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
            withMaven {
                bat "mvn clean package"
            }
        }
    }
}
