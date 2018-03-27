def call(Map config, String title) {
  stage(title) {
    dock.exec(config.name, config.dockerCmd)
  }
}
