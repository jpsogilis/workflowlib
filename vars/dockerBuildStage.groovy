def call(Map config) {
  stage('build') {
    docker.build(config.name, config.buildArgs)
  }
}