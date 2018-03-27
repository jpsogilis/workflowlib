def call(Map config) {
  stage(config.title1) {
    dock.exec(config.name, config.dockerCmd1)
  }
  stage(config.title2) {
    dock.exec(config.name, config.dockerCmd2)
  }
}
