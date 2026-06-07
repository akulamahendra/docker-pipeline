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

        stage ('Maven application build') {
            agent { label 'slave-agent' }
            steps {
                sh 'mvn clean install'
            }
        }

        stage ('Docker Build') {
            agent { label 'slave-agent' }
            steps {
                sh 'docker build -t cardie:v1 .'
            }
        }

        stage ('Docker Run') {
            agent { label 'slave-agent' }
            steps {
                sh 'docker run -d --name cardie-app -p 8080:8080 cardie:v1'
                sh 'Application deployment successful!'
            }
        }
    }
}