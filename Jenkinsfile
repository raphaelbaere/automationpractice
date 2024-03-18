pipeline {
    agent any

    stages {
            stage('Checkout UI Repository') {
                steps {
                    script {
                        checkout scm
                        if (fileExists('repo_ui')) {
                            sh 'rm -rf repo_ui'
                        } else {
                            echo 'Diretório repo_ui não encontrado. Nenhuma ação necessária.'
                        }
                        sh 'git clone -b dev https://github.com/vemser/chronos-qa-ui.git repo_ui'
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
                        sh 'git clone -b dev https://github.com/vemser/chronos-qa-api.git repo_api'
                    }
                }
            }
           stage('Run UI and API Tests') {
               parallel {
                stage('Test UI Repository') {
                    steps {
                        script {
                            echo 'Iniciando etapa de teste para o primeiro repositório...'
                            sh 'cd repo_ui && mvn clean test -Dmaven.test.failure.ignore=true'
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
                [path: 'repo_ui/allure-results'],
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