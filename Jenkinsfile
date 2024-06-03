pipeline {
    agent any
    
    // Definição da instalação do Maven no Jenkins
    tools {
        maven 'MAVEN_HOME' // Nome da instalação do Maven configurada no Jenkins
    }
    
    stages {
        stage('Checkout do Código Fonte') {
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
                // Comandos para executar a análise estática de código
                sleep time: 5, unit: 'SECONDS'
            }
        }
        
        stage('Testes de Integração') {
            steps {
                echo 'Executando testes de integração para verificar o funcionamento conjunto do sistema'
                // Comandos para executar os testes de integração
                sleep time: 5, unit: 'SECONDS'
            }
        }
        
        stage('Análise de Segurança') {
            steps {
                echo 'Realizando análise de segurança estática e/ou dinâmica'
                // Comandos para realizar a análise de segurança
                timeout(time: 1, unit: 'MINUTES') {
                    input message: 'Continuar com a análise de segurança?', ok: 'Sim', submitter: 'admin'
                }
                sleep time: 5, unit: 'SECONDS'
            }
        }
        
        stage('Excluir Contêiner Existente') {
            steps {
                echo 'Verificando e excluindo o contêiner existente, se houver'
                script {
                    // Verificar se o contêiner existe e excluí-lo se necessário
                    sh 'docker rm -f meu-container || true'
                }
            }
        }
        
        stage('Construir Imagem Docker') {
            steps {
                echo 'Construindo a imagem Docker'
                script {
                    // Comando para construir a imagem Docker
                    sh 'docker build -t api-filme-series .'
                }
            }
        }
        
        stage('Criar Novo Container') {
            steps {
                echo 'Criando um novo contêiner Docker'
                script {
                    // Comandos para criar um novo contêiner Docker com um nome diferente
                    sh 'docker run -d --name api-filme-series-novo -p 8089:8089 api-filme-series'
                }
            }
        }
    }
}
