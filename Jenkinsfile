@Library('my-pipeline-library') _

pipeline {
    agent any

	environment {
		MYSQL_USER_NAME = credentials('mysql-user-name')
		MYSQL_USER_PASSWORD = credentials('mysql-user-password')
	}
	options {
	 	buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '1', daysToKeepStr: '15', numToKeepStr: '15')
	}
    stages {
		stage('Setup') {
			steps {
				cleanWs deleteDirs: true, patterns: [[pattern: 'config/*.yml', type: 'INCLUDE']]
				dir('repos/tools') {
					git credentialsId: '868a65c9-9ad7-48a0-b454-364c2f9cc8f0', url: 'git@github.com:CBLPath/tools.git'
				}
			}
		}
        stage('Setup Sandbox') {
            when {
                environment name: 'IMAGE_TYPE', value: 'sandbox'
            }
            steps {
                git branch: 'main', credentialsId: '868a65c9-9ad7-48a0-b454-364c2f9cc8f0', url: 'git@github.com:SHUSA/labis_db_seed.git'
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
                git branch: 'main', credentialsId: '868a65c9-9ad7-48a0-b454-364c2f9cc8f0', url: 'git@github.com:SHUSA/labis_db_seed.git'
				script {
					imageTag = "prod"
					labisDbSeedProduction.database()
					labisDbSeedProduction.secrets()
				}
            }
        }
        stage('Build') {
            steps {
				script {
    				docker.withRegistry('http://10.208.42.130:5000') {
						def image = docker.build("labis_db_seed:${env.BUILD_ID}", '--build-arg MYSQL_USER_NAME --build-arg MYSQL_USER_PASSWORD .')
						echo "Hello ${imageTag}"
					}
				}
            }
        }
    }
}
