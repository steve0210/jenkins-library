@Library('my-pipeline-library') _

pipeline {
    agent any

	options {
	  buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '1', daysToKeepStr: '15', numToKeepStr: '15')
	}
    stages {
        stage('Build Sandbox') {
            when {
                environment name: 'IMAGE_TYPE', value: 'sandbox'
            }
            steps {
				cleanWs deleteDirs: true, patterns: [[pattern: 'config/*.yml', type: 'INCLUDE'], [pattern: 'repos', type: 'INCLUDE']]
                git branch: 'main', credentialsId: '868a65c9-9ad7-48a0-b454-364c2f9cc8f0', url: 'git@github.com:SHUSA/labis_db_seed.git'
				dir('repos/tools') {
					git credentialsId: '868a65c9-9ad7-48a0-b454-364c2f9cc8f0', url: 'git@github.com:CBLPath/tools.git'
				}
				script {
					labisDbSeedSandbox.database()
					labisDbSeedSandbox.secrets()
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
