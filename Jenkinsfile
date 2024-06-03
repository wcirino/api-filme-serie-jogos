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
		   steps {
                sleep(30)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
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
                sleep(30)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
