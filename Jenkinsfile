pipeline {
    agent any

    tools {
        maven "MAVEN"
        nodejs "NODE"
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

                    def message = "# Relatorio de Testes/API e UI/Chronos\n"
                    message += "**Branch:** RELEASE\n"
                    message += "**Build:** ${buildNumber}\n"
                    message += "**Status:** ${buildResult}\n"
                    message += "**Allure Report:** [Allure Report Link](https://bear-above-mole.ngrok-free.app/job/chronos-qa-api-pipeline/${buildNumber}/allure/)"


                    discordSend description: message,
                            image: "${printAllure}",
                            webhookURL: "https://discord.com/api/webhooks/1219110961928077393/OTQYO4L5cIVwvQyokpAzx6B6GFs5E9PZnyxjV54NDraBHDvufYth9Lw_PBFH1kpGcLSe"
                } catch (e) {
                    echo "Erro ao executar notificação para o Discord: ${e.message}"
                }
            }
        }
    }
}
