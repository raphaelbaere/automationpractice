pipeline {
    agent any

    tools {
        maven "MAVEN"
        git "GIT"
    }

    stages {
        stage('Checkout UI Repository') {
            steps {
                script {
                    checkout scm
                }
            }
        }
        stage('Checkout API Repository') {
            steps {
                script {
                    if (fileExists('repo_api')) {
                        sh 'rm -rf repo_api'
                    } else {
                        echo 'Diretório repo_api não encontrado. Nenhuma ação necessária.'
                    }
                    sh 'git clone -b main https://github.com/raphaelbaere/serverrest-test.git repo_api'
                }
            }
        }

        stage('Run UI and API Tests') {
            parallel {
                stage('Test UI Repository') {
                    steps {
                        script {
                            sh 'mvn clean test -Dmaven.test.failure.ignore=true'
                        }
                    }
                }
                stage('Test API Repository') {
                    steps {
                        script {
                            sh 'cd repo_api && mvn clean test -Dmaven.test.failure.ignore=true'
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            allure([
                includeProperties: false,
                jdk: '',
                results: [
                    [path: 'allure-results'],
                    [path: 'repo_api/allure-results']
                ]
            ])
        }
    }
}
