def call(String name, String image = null, String docker_args = null) {
    if (image){
      builder_name = image
    }else{
      builder_name = env.JOB_NAME.toLowerCase() + "_" + name
      docker.build(builder_name, "-f Dockerfile.${name} .")
    }

    if (docker_args.is(null)){ docker_args = "" }

    stage(name){
      sh "docker run -v ${pwd()}:/usr/src/app ${docker_args} ${builder_name}"
    }
}
