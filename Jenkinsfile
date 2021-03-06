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
	stage('Cloud') {
		steps {
			sshPublisher(publishers: [sshPublisherDesc(configName: 'katebegapi', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'sudo systemctl restart myjenkins', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: '**/target/spring-boot-docker-0.0.1-SNAPSHOT.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
		}
	}
    }
	post {
        always {
		emailext body: 
		"""Result : ${currentBuild.currentResult} \n
		Job Name : ${env.JOB_NAME} \n
		Build Number : ${env.BUILD_NUMBER} \n
		Build URL : ${env.BUILD_URL} \n
		Build Name : ${env.BUILD_DISPLAY_NAME} \n
		Base Name Of Job : ${env.JOB_BASE_NAME} \n
		Executor Number : ${env.EXECUTOR_NUMBER} \n
		Build ID : ${env.BUILD_ID} \n
		Jenkins Name : ${env.JENKINS_URL} \n
		Job URL : ${env.JOB_URL}""", subject: 'Jenkins', to: 'devmohamedar990@gmail.com'
        }
    }
}
