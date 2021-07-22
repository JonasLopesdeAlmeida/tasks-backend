
pipeline {
    agent any 
    stages{
        stage ('Build tasksBackend') { 
            steps {
                 bat 'mvn clean package -DskipTests=true' 

            } 
        } 
        stage ('Unit Tests') { 
            steps {
                 bat 'mvn test' 

            }    
        }
        stage ('Deploy Backend') { 
            steps {
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8080/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'

            }    
        }   
        stage ('API Test') { 
            steps {       
                git branch: 'main', url: 'https://github.com/JonasLopesdeAlmeida/tasks-api-test'
                bat 'mvn test' 
            }    
        }         
    }
  
}

