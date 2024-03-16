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
                        sh 'rmdir /s /q repo_api'
                        sh 'git clone -b dev https://github.com/vemser/chronos-qa-api.git repo_api'
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
           stage('Test API Repository') {
                steps {
                    script {
                        sh 'cd repo_api && mvn clean test -Dmaven.test.failure.ignore=true'
                    }
                }
            }
            stage('Publish Allure Report') {
                steps {
                    script {
                        try {
                        sh 'allure generate -o allure-results'

                        archiveArtifacts 'allure-report/**'

                           dir('repo_ui') {
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
                def buildUrl = env.BUILD_URL
                                def buildResult = currentBuild.currentResult
                                def branchName = env.BRANCH_NAME
                                def buildNumber = env.BUILD_NUMBER

                                def printAllure = sh(script: "cd C:\\Users\\rapha && node capture.js ${env.BUILD_NUMBER}", returnStdout: true).trim()
                                def link = "abc"
                                try {
                                    def matcher = (printAllure =~ /https?:\/\/[^\s]+/)
                                    link = matcher.find() ? matcher.group() : "Link não encontrado"
                                } catch (Exception e) {
                                    echo "Erro ao extrair o link da saída do comando: ${e.message}"
                                }

                                def message = "# Relatorio de Testes/API e UI\n"
                                message += "**Branch:** RELEASE\n"
                                message += "**Build:** ${buildNumber}\n"
                                message += "**Status:** ${buildResult}\n"
                                message += "**Allure Report:** [Allure Report Link](/job/exemplo_aula/${buildNumber}/allure/)"


                                discordSend description: message,
                                    image: "${link}",
                                    webhookURL: ""
                } catch (e) {
                    echo "Erro ao executar notificação para o Discord: ${e.message}"
                }
            }
        }
    }
}