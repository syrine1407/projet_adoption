pipeline {
    agent any

    tools {
        maven 'Maven 3.8.5'
        jdk 'jdk17'
    }

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
