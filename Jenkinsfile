@Library('labis-config-files') _

def imageTag
pipeline {
	agent {
	    kubernetes {
	      yaml '''
kind: Pod
spec:
  containers:
  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug
    imagePullPolicy: Always
    command:
    - sleep
    args:
    - 9999999
'''
		}
	}
	options {
	 	buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '1', daysToKeepStr: '15', numToKeepStr: '15')
	}
	environment {
	    GITHUB_CRED = credentials('steve-github-pat')
		MYSQL_CREDS = credentials('labis-mysql-creds')
	}
    stages {
		stage('Setup') {
			steps {
				cleanWs deleteDirs: true, patterns: [[pattern: 'config/*.yml', type: 'INCLUDE']]
				dir('repos/tools') {
					git url: "https://${env.GITHUB_CRED}@github.com/SHUSA/tools.git"
				}
			}
		}
        stage('Setup Sandbox') {
            when {
                environment name: 'IMAGE_TYPE', value: 'sandbox'
            }
            steps {
                git branch: 'main', url: "https://${env.GITHUB_CRED}@github.com/SHUSA/labis_db_seed.git"
				script {
					imageTag = "dev"
					labisDbSeedSandbox.database()
					labisDbSeedSandbox.secrets()
				}
            }
        }
        stage('Setup Production') {
            when {
                environment name: 'IMAGE_TYPE', value: 'production'
            }
            steps {
                git branch: 'main', url: "https://${env.GITHUB_CRED}@github.com/SHUSA/labis_db_seed.git"
				script {
					imageTag = "prod"
					labisDbSeedProduction.database()
					labisDbSeedProduction.secrets()
				}
            }
        }
        stage('Build') {
            steps {
            	container(name: 'kaniko', shell: '/busybox/sh') {
                	sh """#!/busybox/sh
                        /kaniko/executor --context='.' --build-arg MYSQL_USER_NAME=${env.MYSQL_CREDS_USR} --build-arg MYSQL_USER_PASSWORD=${env.MYSQL_CREDS_PSW} --insecure --insecure-registry=10.208.42.130:5000 --destination=10.208.42.130:5000/labis_db_seed:${env.BUILD_ID} --destination=10.208.42.130:5000/labis_db_seed:${imageTag}
                    """
				}
				echo "Hello ${env.MYSQL_CREDS_USR} : ${env.MYSQL_CREDS_PSW}"
            }
        }
    }
}
