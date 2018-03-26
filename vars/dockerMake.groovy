def call(name) {
    docker.build('squarescale/qa-front-utils', '-f Dockerfile.jenkins .')
          .inside("-v ${pwd()}:${pwd()} --group-add=992 --workdir ${pwd()} -v /var/run/docker.sock:/var/run/docker.sock") {
      sh 'env=staging make all'
    }
}
