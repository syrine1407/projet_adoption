pipeline {
    agent any

    tools {
        jdk 'jdk17'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Tests unitaires') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
