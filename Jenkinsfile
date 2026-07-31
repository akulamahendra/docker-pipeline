pipeline {
    agent any
    
    environment {
            AWS_ACCESS_KEY_ID = credentials('aws-access-key-id')
            AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
            AWS_REGION = 'ap-south-1'
    }
    stages{
        stage('clone repository'){
            steps{
                git 'https://github.com/akulamahendra/docker-pipeline.git'
            }
        }
        stage ('Test the application') {           
            steps {
                sh 'mvn test'
            }
        }

        stage ('Verify the code coverage') {
            steps {
                sh 'mvn verify'
            }
        }

        stage ('Maven application build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('docker image'){
            steps{
                sh 'docker build -t myapp .'
            }
        }

        stage('docker build container'){
            steps{
                sh 'docker run -d --name flaskapp -p 3000:8080 myapp'
            }
        }
    }

}

