def call(Map config, String title = 'test') {
  stage(title) {
    dock.exec(config.name, config.dockerCmd)
  }
}
