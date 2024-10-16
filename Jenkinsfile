pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('DOCKER_HUB_CREDENTIAL')
        VERSION = "${env.BUILD_ID}"
        GIT_REPO = "git@github.com:Rutzno/fda-deployment.git"
        GIT_BRANCH = "main"
    }

    tools {
        maven "Maven"
    }

    stages {
        stage("Maven BUILD") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage("Run TESTS") {
            steps {
                sh "mvn test"
            }
        }

        stage("Sonar Analysis QUALITY") {
            steps {
                sh "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.host.url=http://35.180.137.8:9000/ -Dsonar.login=squ_32789bcdadb6e4337e432d6cbc100c2a1a14fde5"
            }
        }

        stage("Check code Coverage QUALITY") {
            steps {
                script {
                    def token = ""
                    def sonarQubeUrl = "http://:9000/api"
                    def componentKey = "com.diarpy:restaurantListing-service"
                    def coverageThreshold = 80.0
                    def response = sh (
                        script: "curl -H 'Authorization: Bearer ${token}' '${sonarQubeUrl}/measures/component?component=${componentKey}&metricKeys=coverage'",
                        returnStdout: true
                    ).trim()
                    def coverage = sh (
                        script: "echo '${response}' | jq -r '.component.measures[0].value'",
                        returnStdout: true
                    )
                    echo "Coverage: ${coverage}"
                    if (coverage < coverageThreshold) {
                        error "Coverage is below the threshold of ${coverageThreshold}%. Aborting the pipeline."
                    }
                }
            }
        }

        stage("Docker Build & Push PACKAGE") {
            steps {
                sh "echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin",
                sh "docker build -t macktb/restaurantlisting-service:${VERSION} ."
                sh "docker push macktb/restaurantlisting-service:${VERSION}"
            }
        }

        stage("Cleanup Workspace") {
            steps {
                deleteDir()
            }
        }

        stage("Update Image Tag in GitOps") {
            steps {
                checkout scmGit(branches: [[name: '*/${GIT_BRANCH}']], extensions: [], userRemoteConfigs: [[ credentialsId: 'git-ssh', url: ${GIT_REPO}]])
                script {
                    // Set the new image tag with the Jenkins build number
                    sh '''
                        sed -i "s/image:.*/image: macktb\\/restaurantlisting-service:${VERSION}/" aws/restaurant-manifest.yml
                    '''
                    sh "git checkout ${GIT_BRANCH}"
                    sh "git add ."
                    sh "git commit -m 'Update image tag to ${VERSION}'"
                    sshagent(["git-ssh"]) {
                        sh("git push")
                    }
                }
            }
        }
    }
}