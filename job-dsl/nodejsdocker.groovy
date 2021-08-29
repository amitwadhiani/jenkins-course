job('NodeJS Docker example') {
    scm {
        git('git://github.com/amitwadhiani/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@amitwadhiani.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('amit0wadhiani/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('998ffb10-3286-4316-9fad-ef16a26aaa52')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
job('Angular RealWorld Frontend') {
    scm {
        git('https://github.com/amitwadhiani/frontendDemo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('amit.wadhiani@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        shell("npm install -g @angular/cli@latest && npm install --global yarn && yarn install")
        dockerBuildAndPublish {
            repositoryName('amit0wadhiani/angularRealworldFrontend')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('998ffb10-3286-4316-9fad-ef16a26aaa52')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}