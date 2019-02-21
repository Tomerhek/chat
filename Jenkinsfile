node {
    def server
    def buildInfo
    def rtMaven

    stage ('Clone') {
        git url: 'https://github.com/Tomerhek/chat'
    }

    stage ('Artifactory configuration') {
        // Obtain an Artifactory server instance, defined in Jenkins --> Manage:
        server = Artifactory.server 'artifactoryID'

        rtMaven = Artifactory.newMavenBuild()
        rtMaven.tool = "Maven-3.3.9" // Tool name from Jenkins configuration
        rtMaven.deployer releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local', server: server
        rtMaven.resolver releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: server
        rtMaven.deployer.deployArtifacts = false // Disable artifacts deployment during Maven run

        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Test') {
        rtMaven.run pom: 'pom.xml', goals: 'clean test'
    }

    stage ('Install') {
        rtMaven.run pom: 'pom.xml', goals: 'install', buildInfo: buildInfo
    }

    stage ('Deploy') {
        rtMaven.deployer.deployArtifacts buildInfo
    }

    stage ('Publish build info') {
        server.publishBuildInfo buildInfo
    }
}