pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Tests unitaires') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
