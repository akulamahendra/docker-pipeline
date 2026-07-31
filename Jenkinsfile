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
     stage('docker image'){
            steps{
                sh 'docker build -t myapp .'
            }
        }

        stage('docker build container'){
            steps{
                sh 'docker run -d --name flaskapp -p 3000:80 myapp'
            }
        }
    }

}

