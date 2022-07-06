// @Library('my-pipeline-library') _

def githubcreds = 'ff90f0e9-54be-4568-a808-2ed15a552f17'
def imageTag

pipeline {
    agent any

	environment {
		MYSQL_CREDS = credentials('labis-mysql-creds')
		MYSQL_USER_NAME = env.MYSQL_CREDS_USR
		MYSQL_USER_PASSWORD = env.MYSQL_CREDS_PSW
	}
	options {
	 	buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '1', daysToKeepStr: '15', numToKeepStr: '15')
	}
    stages {
		stage('Setup') {
			steps {
				cleanWs deleteDirs: true, patterns: [[pattern: 'config/*.yml', type: 'INCLUDE']]
				dir('repos/tools') {
					git credentialsId: githubcreds, url: 'git@github.com:CBLPath/tools.git'
				}
			}
		}
        stage('Setup Sandbox') {
            when {
                environment name: 'IMAGE_TYPE', value: 'sandbox'
            }
            steps {
                git branch: 'main', credentialsId: githubcreds, url: 'git@github.com:SHUSA/labis_db_seed.git'
				script {
					imageTag = "dev"
					// labisDbSeedSandbox.database()
					// labisDbSeedSandbox.secrets()
				}
            }
        }
        stage('Setup Production') {
            when {
                environment name: 'IMAGE_TYPE', value: 'production'
            }
            steps {
                git branch: 'main', credentialsId: githubcreds, url: 'git@github.com:SHUSA/labis_db_seed.git'
				script {
					imageTag = "prod"
					// labisDbSeedProduction.database()
					// labisDbSeedProduction.secrets()
				}
            }
        }
        stage('Build') {
            steps {
/*
				script {
    				docker.withRegistry('http://10.208.42.130:5000') {
						def image = docker.build("labis_db_seed:${env.BUILD_ID}", '--build-arg MYSQL_USER_NAME --build-arg MYSQL_USER_PASSWORD .')
						echo "Hello ${imageTag}"
						image.push(imageTag)
					}
				}
*/
            }
        }
    }
}
