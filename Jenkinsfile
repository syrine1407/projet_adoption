pipeline {
    agent any

    tools {
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
