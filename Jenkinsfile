@Library('my-pipeline-library') _

pipeline {
    agent any

    stages {
        stage('Build Sandbox') {
            when {
                environment name: 'IMAGE_TYPE', value: 'sandbox'
            }
            steps {
                git branch: 'main', credentialsId: '868a65c9-9ad7-48a0-b454-364c2f9cc8f0', url: 'git@github.com:SHUSA/labis_db_seed.git'
				dir('repos/tools') {
					git credentialsId: '868a65c9-9ad7-48a0-b454-364c2f9cc8f0', url: 'git@github.com:CBLPath/tools.git'
				}
            }
        }
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
}
