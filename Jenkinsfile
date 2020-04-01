pipeline {

    environment {
        //This variable need be tested as string
        doError = '1'
    }
    agent any

    options {
        skipStagesAfterUnstable()
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'

            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') { 
            steps {
                sh './jenkins/scripts/deliver.sh' 
            }
        }	
    }
	post {
        always {
            	emailext body: 'A Test EMail', recipientProviders: [[$class: 'mohamed_rmdan7@hotmail.com'], [$class: 			'devmohamed990@gmail.com']], subject: 'Test'
        }
    }
}
