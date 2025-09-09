node{
    stage('Checkout'){
        checkout scm
    }
    stage('Build'){
        echo 'Building...'
        sh '''
            docker build -t my-app:latest .
        '''
    }
    stage('Test') {
        echo 'Testing...'
        sh '''
            docker run --rm my-app:latest
        '''
    }
}