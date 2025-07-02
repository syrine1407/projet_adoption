pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/ton-compte/ton-projet.git'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=TON_TOKEN -Dsonar.host.url=http://localhost:9000'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }
        stage('Docker Build and Push') {
            steps {
                sh 'docker build -t tonutilisateur/tonimage:latest .'
                sh 'docker push tonutilisateur/tonimage:latest'
            }
        }
        stage('Docker Compose Up') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}
