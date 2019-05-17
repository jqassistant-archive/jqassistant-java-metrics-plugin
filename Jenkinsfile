node {

  stage('Configure') {
    env.JAVA_HOME="${tool 'jdk8'}"
    env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
    sh 'java -version'
  }

  stage('Checkout') {
    checkout scm
  }

  stage('Build') {
    def mvnHome = tool 'Maven-3.5.0'
    sh "${mvnHome}/bin/mvn clean verify site -B -V -U -Pqa"
  }

//  stage('Result') {
//    junit '**/target/surefire-reports/TEST*.xml, **/target/failsafe-reports/TEST*.xml'
//    jacoco classPattern: '**/target/classes', execPattern: '**/target/coverage-reports/jacoco**.exec', sourcePattern: '**/src/main/java'
//
//    recordIssues tools: [checkStyle(pattern: '**/target/checkstyle-result.xml'),
//                         spotBugs(pattern: '**/target/spotbugsXml.xml'),
//                         pmdParser(pattern: '**/target/pmd.xml'),
//                         cpd(pattern: '**/target/cpd.xml'),
//                         taskScanner(highTags:'FIXME', normalTags:'TODO', includePattern: '**/*.java', excludePattern: 'target/**/*')]
//
//    dependencyCheckPublisher canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/dependency-check-report.xml', unHealthy: ''
//
//    //publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/generated-docs/', reportFiles: 'arc42-template.html', reportName: 'arc42-Template', reportTitles: ''])
//  }
//
//  stage('Archive') {
//    archiveArtifacts 'target/tutorials*.jar, target/tutorials*project.zip'
//  }

}
