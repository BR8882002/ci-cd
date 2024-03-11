job('NodeJS Docker example') {
    scm {
        git('https://github.com/BR8882002/ci-cd.git','*/main') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL NodeJs User')
            node / gitConfigEmail('jenins-dsl@domain.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('romanodocker888/jenkines_lab')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

