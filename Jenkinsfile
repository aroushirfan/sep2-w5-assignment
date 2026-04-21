pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        JAVA_HOME = 'C:\Program Files\Java\jdk-21.0.10'
        SONARQUBE_SERVER = 'SonarQubeServer'
        SONAR_TOKEN = 'squ_c5e6e519dcbf9c26b4929cfeb5fc2b2dcb5b6c1f'
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-creds'
        DOCKERHUB_REPO = 'aroushi/w5_assignment'
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/aroushirfan/sep2-w3-assignment.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    bat """
                    ${tool 'SonarScanner'}\\bin\\sonar-scanner ^
                    -Dsonar.projectKey=w3-assignment ^
                    -Dsonar.sources=src/main/java ^
                    -Dsonar.tests=src/test/java ^
                    -Dsonar.java.binaries=target/classes ^
                    -Dsonar.host.url=http://localhost:9000 ^
                    -Dsonar.login=${env.SONAR_TOKEN}
                    """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}
