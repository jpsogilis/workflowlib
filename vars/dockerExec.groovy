def call(Map config) {
  stage('Launch Ganache') {
    dock.exec(config.name, dockerCmd: 'ganache-cli &')
  }
  stage(config.title1) {
    dock.exec(config.name, config.dockerCmd1)
  }
  stage(config.title2) {
    dock.exec(config.name, config.dockerCmd2)
  }
}
