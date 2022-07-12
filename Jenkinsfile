@Library('labis-config-files') _

def imageTag
pipeline {
    agent any

	options {
	 	buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '1', daysToKeepStr: '15', numToKeepStr: '15')
	}
	environment {
	    GITHUB_CRED = credentials('steve-github-pat')
		MYSQL_CREDS = credentials('labis-mysql-creds')
		MYSQL_USER_NAME = "${env.MYSQL_CREDS_USR}"
		MYSQL_USER_PASSWORD = "${env.MYSQL_CREDS_PSW}"
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
				script {
					def image = docker.build("labis_db_seed:${env.BUILD_ID}", '--build-arg MYSQL_USER_NAME --build-arg MYSQL_USER_PASSWORD .')
				}
				echo "Hello ${env.MYSQL_USER_NAME} : ${env.MYSQL_USER_PASSWORD}"
            }
        }
    }
}
