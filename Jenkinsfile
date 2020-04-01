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
	stage('Send email') {
	    def mailRecipients = "devmohamed@gmail.com"
	    def jobName = currentBuild.fullDisplayName

	    emailext body: '''${SCRIPT, template="groovy-html.template"}''',
		mimeType: 'text/html',
		subject: "[Jenkins] ${jobName}",
		to: "${mailRecipients}",
		replyTo: "${mailRecipients}",
		recipientProviders: [[$class: 'CulpritsRecipientProvider']]
	}
    }
}
