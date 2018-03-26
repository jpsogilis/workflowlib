def call(Closure body = null) {
  node {
    //try {
      def sqsc_env = env.BRANCH_NAME == 'master' ? 'staging' :
        env.BRANCH_NAME == 'production' ? 'production' :
        'dev'
      def shortId = null

      stage("checkout") {
        def scmVars = checkout scm
        shortId = scmVars.GIT_COMMIT.take(7)
      }

      body()
    //} catch(e) {
    //  throw e
    //} finally {
    //  dock.cleanup(containerNames)
    //}
  }
}