pipeline {
    agent any
    options {
        disableConcurrentBuilds()
        timestamps()
    }
    triggers { 
        cron("@midnight")
    }
    stages {
        stage("Package") {
            steps {
                withMaven() {
                    bat "mvn clean package"
                }
            }
        }
    }
}
