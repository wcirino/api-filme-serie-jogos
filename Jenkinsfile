pipeline{
    agent   any
    stages  {
        stage('Build backend'){
            steps{
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Junit Test'){
           steps{
                bat 'mvn test'
            }
        }
       stage('Sonar analysis'){
		   environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=9df79ae97ca7935d37e1e4dae863dbd80915aa6a -Dsonar.sources=src/main/java,src/main/resources -Dsonar.java.binaries=target  -Dsonar.exclusions=**/**/Main.java,src/main/resources/**.properties,src/main/java/com/clinica/config/**.java,src/main/java/com/clinica/dto/**.java,src/main/java/com/clinica/entity/**.java,src/main/java/com/clinica/repository/**.java"
                }
            }
        }
		stage ('Quality Gate') {
            steps {
                sleep(15)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
		stage ('Deploy Backend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'Clinica-0.0.1-SNAPSHOT', war: 'target/Clinica-0.0.1-SNAPSHOT.war'
            }
        }
    }
}
