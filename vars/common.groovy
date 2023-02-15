def getLastSuccessfulBuildNumber(projectName){
  return Jenkins.instance.getItemByFullName(projectName).lastSuccessfulBuild.number
}
