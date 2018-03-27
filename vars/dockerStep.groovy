def call(Map config) {
  dock.build(config.name)
  dock.run(config.name, config.dockerArgs)
  dockExec(config.title1, config.name, config.dockerCmd1)
  dockExec(config.title2, cinfig.name, config.dockerCmd2)
  dock.copy(config.name, config.toCopy)
  dock.unitTests(config.name, config.junitFile)
  dock.rm(config.name)
}
