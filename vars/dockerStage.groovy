def call(Map config) {
  stage(config.name) {
    dockerStep(config)
  }
}