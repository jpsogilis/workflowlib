def call(Closure body = null) {
    node {
      checkout scm
      body()
    }
}
