def githubcreds = 'ff90f0e9-54be-4568-a808-2ed15a552f17'
def imageTag

pipeline {
    agent any

	environment {
		MYSQL_CREDS = credentials('labis-mysql-creds')
		MYSQL_USER_NAME = "${env.MYSQL_CREDS_USR}"
		MYSQL_USER_PASSWORD = "${env.MYSQL_CREDS_PSW}"
	}
    stages {
		stage('Setup') {
			steps {
				dir('repos/tools') {
					git credentialsId: githubcreds, url: 'git@github.com:CBLPath/tools.git'
				}
			}
		}
        stage('Build') {
            steps {
				echo "Hello ${MYSQL_CREDS} : ${MYSQL_CREDS_USR} : ${MYSQL_CREDS_PSW}"
				echo "Hello ${MYSQL_USER_NAME} : ${MYSQL_USER_PASSWORD}"
            }
        }
    }
}
