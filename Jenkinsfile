pipeline {
    agent any



    stages {
        stage('Checkout API Repository') {
            steps {
                script {
                    checkout scm
                }
            }
        }
       stage('Test UI Repository') {
            steps {
                script {
                    echo 'Iniciando etapa de teste para o primeiro repositório...'
                    sh 'mvn -e clean test -Dmaven.test.failure.ignore=true'
                }
            }
        }
        stage('Checkout UI Repository') {
            steps {
                script {
                    sh 'rm -rf repo_api'
                    sh 'git clone -b dev https://github.com/vemser/chronos-qa-api.git repo_api'
                }
            }
        }
        stage('Test API Repository') {
            steps {
                script {
                    bat 'cd repo_api && mvn clean test -Dmaven.test.failure.ignore=true'
                }
            }
        }
        stage('Publish Allure Report') {
            steps {
                script {
                    try {
                    sh 'allure generate -o allure-results'

                    archiveArtifacts 'allure-report/**'

                       dir('repo_api') {
                            sh "allure generate -o allure-results"
                            def resultAPI = currentBuild.result
                        }
                    } catch (e) {
                    echo "Erro ao executar Allure Report: ${e.message}"
                    }
                    echo 'Arquivos de relatório Allure arquivados.'
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
            echo 'Pós-processamento concluído.'
            script {
                try {
                } catch (e) {
                    echo "Erro ao executar notificação para o Discord: ${e.message}"
                }
            }
        }
    }
}