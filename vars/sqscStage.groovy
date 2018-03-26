def call(Map config) {
    img = docker.build(config.name, "-f docker/${config.name}/Dockerfile .")
    cnt = img.run()
    sh "docker logs -f ${cnt.id}"
    status = sh(script:"docker inspect ${cnt.id} --format='{{.State.ExitCode}}'", returnStdout: true).trim()
    if status.toInteger() != 0 {
      cnt.stop()
      error("Container ${config.name} exited with non-zero code, aborting ...")
    }
    copy(cnt.id, config.toCopy)
    cnt.stop()
}

@NonCPS
def copy(String cnt, Map toCopy){
  toCopy.each { src, dst ->
    sh "docker cp ${cnt}:${src} ${dst}"
  }
}
