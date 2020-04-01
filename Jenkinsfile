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
		emailext body: '${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\\n More info at: 
${env.BUILD_URL}', subject: 'Jenkins', to: 'devmohamedar990@gmail.com'
        }
    }
}
