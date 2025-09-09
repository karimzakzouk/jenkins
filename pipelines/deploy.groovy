node {
    stage('Checkout'){
        checkout scm
    }
    stage('Build') {
        echo 'Building...'
        sh '''
            docker build -t my-app:latest .
        '''
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
                docker push my-app:latest
            '''
        }
    }

}