node {
    stage('Checkout'){
        checkout scm
    }

    stage('Build') {
        withCredentials([
            usernamePassword(
                credentialsId: 'docker-hub-credentials',
                usernameVariable: 'DOCKERHUB_USERNAME',
                passwordVariable: 'DOCKERHUB_PASSWORD'
            )
        ]) {
            sh '''
                docker build -t $DOCKERHUB_USERNAME/my-app:latest .
            '''
        }
    }

    stage('Docker Login and Push') {
        withCredentials([
            usernamePassword(
                credentialsId: 'docker-hub-credentials',
                usernameVariable: 'DOCKERHUB_USERNAME',
                passwordVariable: 'DOCKERHUB_PASSWORD'
            )
        ]) {
            sh '''
                echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin
                docker push $DOCKERHUB_USERNAME/my-app:latest
                docker rmi $DOCKERHUB_USERNAME/my-app:latest || true
            '''
        }
    }
}
