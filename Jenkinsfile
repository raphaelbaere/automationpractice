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