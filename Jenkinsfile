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
			environment {
				IMAGE_TAG = "dev"
			}
            steps {
                git branch: 'main', credentialsId: '868a65c9-9ad7-48a0-b454-364c2f9cc8f0', url: 'git@github.com:SHUSA/labis_db_seed.git'
				script {
					image_tag = "ddd"
					labisDbSeedSandbox.database()
					labisDbSeedSandbox.secrets()
				}
            }
        }
        stage('Setup Production') {
            when {
                environment name: 'IMAGE_TYPE', value: 'production'
            }
			environment {
				IMAGE_TAG = "prod"
			}
            steps {
                git branch: 'main', credentialsId: '868a65c9-9ad7-48a0-b454-364c2f9cc8f0', url: 'git@github.com:SHUSA/labis_db_seed.git'
				script {
					labisDbSeedProduction.database()
					labisDbSeedProduction.secrets()
				}
            }
        }
        stage('Build') {
            steps {
                echo "Hello ${IMAGE_TAG} : ${image_tag}"
            }
        }
    }
}
