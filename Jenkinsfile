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

        stage('Checkout Screenshot') {
            steps {
                script {
                    if (fileExists('screenshot')) {
                        sh 'rm -rf screenshot'
                    } else {
                        echo 'Diretório repo_api não encontrado. Nenhuma ação necessária.'
                    }
                    sh 'git clone -b main https://github.com/raphaelbaere/jenkins_pipeline_screenshot.git screenshot'
                    sh 'cd screenshot && npm install'
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

                    def WEBHOOK_URL = "https://discord.com/api/webhooks/1219148560533159946/e8GyMfyFdxhiqKz16X23Z_tkrKBoCH6qQFERYKgqyF6gwJi-6z7fpOlA6m_-XCDdZo4y"

                    def message = "# Relatorio de Testes/API e UI/\n"
                    message += "**Branch:** MAIN\n"
                    message += "**Build:** ${buildNumber}\n"
                    message += "**Status:** ${buildResult}\n"

                    def screenshotPath = sh(script: "cd screenshot && node capture.js ${env.JOB_NAME} ${env.BUILD_NUMBER} ${currentBuild.currentResult} ${message} https://discord.com/api/webhooks/1219148560533159946/e8GyMfyFdxhiqKz16X23Z_tkrKBoCH6qQFERYKgqyF6gwJi-6z7fpOlA6m_-XCDdZo4y", returnStdout: true).trim()
                } catch (e) {
                    echo "Erro ao executar notificação para o Discord: ${e.message}"
                }
            }
        }
    }
}
