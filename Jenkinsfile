pipeline {
    agent any
    
    stages {
        stage('Checkout do Cod Font') {
            steps {
                echo 'Clonando ou fazendo o checkout do repositório de código-fonte'
                // Comandos para realizar o checkout do código-fonte
                sh 'echo Realizando o checkout do código-fonte'
                sleep time: 5, unit: 'SECONDS'
            }
        }
        
        stage('Compilação/Build') {
            steps {
                echo 'Compilando o código-fonte para gerar artefatos executáveis ou pacotes'
                sh 'mvn clean package -DskipTests=true'
            }
        }
        
        stage('Testes Unitários') {
            steps {
                echo 'Executando testes unitários para garantir a corretude do código'
                sh 'mvn test'
            }
        }
        
        stage('Análise Estática de Código') {
            steps {
                echo 'Executando análise estática de código para identificar problemas de qualidade'
                sleep time: 5, unit: 'SECONDS'
            }
        }
        
        stage('Testes de Integração') {
            steps {
                echo 'Executando testes de integração para verificar o funcionamento conjunto do sistema'
                sleep time: 5, unit: 'SECONDS'
            }
        }
        
        stage('Análise de Segurança') {
            steps {
                script {
                    def userInput = input message: 'Continuar com a análise de segurança?', parameters: [choice(name: 'CONTINUAR_ANALISE', choices: 'Sim\nNão', description: 'Escolha Sim para continuar ou Não para interromper')]
                    if (userInput == 'Não') {
                        error 'Análise de segurança interrompida pelo usuário'
                    }
                }
                echo 'Realizando análise de segurança estática e/ou dinâmica'
                sleep time: 5, unit: 'SECONDS'
            }
        }
        
        stage('Implantação em Ambiente de Desenvolvimento/Testing - testando') {
            steps {
                echo 'Implantando o aplicativo em um ambiente de teste para validação'
                sleep time: 5, unit: 'SECONDS'
            }
        }
    }
}
