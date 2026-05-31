pipeline {
    agent none

    stages {
        stage ('Test the application') {
            agent { label 'slave-agent' }            
            steps {
                sh 'mvn test'
            }
        }

        stage ('Verify the code coverage') {
            agent { label 'slave-agent' }
            steps {
                sh 'mvn verify'
            }
        }

        stage ('Build the application') {
            agent { label 'slave-agent' }
            steps {
                sh 'mvn clean install'
            }
        }
        
        stage ('Copy WAR') {
            agent { label 'slave-agent' }
            steps {
                sh 'sudo cp -v target/*.war /var/lib/tomcat10/webapps/'
            }
        }
    }
}