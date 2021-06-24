
pipeline {
    agent any 
    stages{
        stage ('Build tasksBackend') { 
            steps {
                 bat 'mvn clean package -DskipTests=true' 

            }   
        }   
    }
}
