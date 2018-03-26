def call(Map config) {
  dock.build(config.name)
  dock.run(config.name, config.dockerArgs)
  dock.copy(config.name, config.toCopy)
  dock.unitTests(config.name, config.junitFile)
  dock.rm(config.name)
}