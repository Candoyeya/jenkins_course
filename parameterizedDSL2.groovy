job('example2-job-dsl') {
  description('Example JOB DSL for Jenkins Course')
  scm {
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
      node / gitConfigName('Candoyeya')
      node / gitConfigEmail('telematik_4@hotmail.com')
    }
  }
  parameters {
  	stringParam('nombre', defaultValue = 'Alejandro', description = 'Parametro de cadena para el Job Booleano')
    choiceParam('planeta', ['Mercurio','Venus','Tierra','Marte','Jupiter','Saturno','Urano','Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
    cron('H/4 * * * *')
    githubPush()
  }
  steps {
  	shell("bash jobscript.sh")
  }
  publishers {
  	mailer('telematik_4@hotmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
