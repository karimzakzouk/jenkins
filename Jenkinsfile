pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    echo 'Building..'
                    load 'pipelines/test.groovy'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying....'
                    load 'pipelines/deploy.groovy'
                }
            }
        }
    }
}