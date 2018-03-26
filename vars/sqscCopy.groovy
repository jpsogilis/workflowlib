def call(String name, String from, String to) {
  contName = "${env.JOB_NAME}_${env.BUILD_NUMBER}_${name}".replaceAll('/', '_')
  sh "docker cp ${contName}:${from} ${to}"
}
