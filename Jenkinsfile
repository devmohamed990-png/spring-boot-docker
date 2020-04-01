pipeline {
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

 	stage('Mail') { 
            steps {
                def emailBody = '${SCRIPT, template="regressionfailed.groovy"}'
		def emailSubject = "${env.JOB_NAME} - Build# ${env.BUILD_NUMBER} - ${env.BUILD_STATUS}"
		emailext(mimeType: 'text/html', replyTo: 'devmohamed990@gmail.com', subject: emailSubject, to: 'devmohamed990@gmail.com', body: emailBody) 
            }
        }
	
    }
}
