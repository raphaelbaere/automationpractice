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
                    def buildNumber = env.BUILD_NUMBER
                    def clientIdImgur = "a355bf1c455a135"
                    def message = "# Relatorio de Testes/API e UI/Chronos\n"
                    message += "**Branch:** RELEASE\n"
                    message += "**Build:** ${buildNumber}\n"
                    message += "**Status:** ${buildResult}\n"
                    message += "**Allure Report:** [Allure Report Link](https://bear-above-mole.ngrok-free.app/job/chronos-qa-api-pipeline/${buildNumber}/allure/)"

                    def screenshotPath = sh(script: "cd screenshot && node capture.js ${env.BUILD_NUMBER} ${env.JOB_NAME} ${clientIdImgur}", returnStdout: true).trim()

                    def WEBHOOK_URL="https://discord.com/api/webhooks/1219110961928077393/OTQYO4L5cIVwvQyokpAzx6B6GFs5E9PZnyxjV54NDraBHDvufYth9Lw_PBFH1kpGcLSe"
                    def MESSAGE="Mensagem de exemplo com imagem"
                    def IMAGE_PATH="screenshot/screenshot.png"
                    def IMAGE_BASE64= sh(script: "base64 -w 0 ${IMAGE_PATH}", returnStdout: true).trim()

                    // Construir a carga útil JSON
                    def PAYLOAD="{\"content\":\"${MESSAGE}\",\"file\":\"data:image/jpeg;base64,${IMAGE_BASE64}\"}"

                    // Enviar a mensagem com a imagem para o webhook do Discord
                    sh "curl -X POST -H 'Content-Type: application/json' -d '${PAYLOAD}' '${WEBHOOK_URL}'"
                } catch (e) {
                    echo "Erro ao executar notificação para o Discord: ${e.message}"
                }
            }
        }
    }
}
