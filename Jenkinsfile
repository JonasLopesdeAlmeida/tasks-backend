
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
    }
  
}

