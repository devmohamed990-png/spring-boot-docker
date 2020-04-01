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
		emailext body: """Result : ${currentBuild.currentResult}
		Job Name : ${env.JOB_NAME}
		Build Number : ${env.BUILD_NUMBER}
		URL : ${env.BUILD_URL}""", subject: 'Jenkins', to: 'devmohamedar990@gmail.com'
        }
    }
}
