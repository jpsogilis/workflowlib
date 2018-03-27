def call(Map config) {
    dock.exec(config.name, config.titleExec, config.dockerCmd, config.dockerArgs)
}
