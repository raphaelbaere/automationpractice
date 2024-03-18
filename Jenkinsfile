pipeline {
    agent any

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
                            echo 'Iniciando etapa de teste para o primeiro repositório...'
                            sh 'mvn -e clean test -Dmaven.test.failure.ignore=true'
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