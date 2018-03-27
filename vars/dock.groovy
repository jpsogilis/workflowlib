import groovy.transform.Field

@NonCPS
def build(String name) {
  docker.build(builderName(name), " --no-cache -f docker/${name}/Dockerfile .")
}

@NonCPS
def run(String name, String dockerArgs = "") {
  def dArgs = dockerArgs == null ? "" : dockerArgs
  sh "docker run --name ${containerName(name)} ${dArgs} ${builderName(name)}"
}

@NonCPS
def unitTests(String name, String junitFile) {
  if (junitFile != null) {
    def toCopy = [:]
    toCopy[junitFile] = 'tests.xml'
    copy(name, toCopy)
    junit('tests.xml')
  }
}

@NonCPS
def copy(String name, Map toCopy) {
  if (toCopy != null) {
    toCopy.each { src, dst ->
      cp(name, src, dst)
    }
  }
}

@NonCPS
def builderName(String name) {
  return "${env.JOB_NAME}_${env.BRANCH_NAME}_${env.BUILD_NUMBER}_${name}"
}

@NonCPS
def containerName(String name) {
  return "${builderName(name).replaceAll('/', '_')}_container"
}

@NonCPS
def cp(String name, String filesIn, String filesOut) {
  sh "docker cp ${containerName(name)}:${filesIn} ${filesOut}"
}

@NonCPS
def stop(String name) {
  sh "docker stop ${containerName(name)}"
}

@NonCPS
def rm(String name) {
  sh "docker rm ${containerName(name)}"
}

@NonCPS
def exec(String name, String dockerCmd = "", String dockerArgs = "") {
  if (dockerCmd != null) {
    def dArgs = dockerArgs == null ? "" : dockerArgs
    sh "docker exec ${dArgs} ${containerName(name)} ${dockerCmd} && exit 0"
  }
}

@NonCPS
def cleanup(List containers) {
  stage('Cleanup') {
    containers.each { name ->
      sh "docker kill ${containerName(name)} || true"
      sh "docker rm ${containerName(name)} || true"
    }
    sh "docker run --rm -v ${pwd()}:/usr/src/app alpine:latest sh -c \\\"rm -Rf /usr/src/app/*\\\""
  }
}
