pipeline {
    agent any
    options {
        disableConcurrentBuilds()
        timestamps()
    }
    triggers { 
        cron("@nightly")
    }
    stages {
        stage("Package") {
            steps {
                withMaven {
                    bat "mvn clean package"
                }
            }
        }
    }
}
