
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
    }
  
}
