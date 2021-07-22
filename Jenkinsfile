
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
                dir('api-test')  {
                    git branch: 'main', url: 'https://github.com/JonasLopesdeAlmeida/tasks-api-test'
                    bat 'mvn test' 
                }
            }    
         }
        stage ('Deploy Frontend') { 
            steps {
                dir('frontend')  {
                    git branch: 'main', url: 'https://github.com/JonasLopesdeAlmeida/tasks-frontend'
                    bat 'mvn clean package' 
                    deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8080/')], contextPath: 'tasks', war: 'target/tasks.war'
                }
            }    
        }            
     }
  }

