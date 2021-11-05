job('ejemplo-job-github'){
	description('JOB DE EJEMPLO')
    scm {
        git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { 
            node / gitConfigName('Jessica Estrada')
            node / gitConfigEmail('jessica.estrada.robreno@gmail.com')
        }
	}
  	parameters{
  		stringParam('nombre', defaultValue = "Jess", description = "Paramtro de cadena")
      	choiceParam('planeta', ['tierra','jupiter', 'urano','pluton'])
      	booleanParam('agente', false)
  	}
    triggers{
      cron('H/7 * * * *')
      githubPush()
    }
    steps{
      shell("bash jobscript.sh")
    }
  	publishers{
  		mailer('Jessica.Lissette.Estrada.Robreno@kyndryl.com',true,true)
       	
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
          	sendAs(null)
           	commitInfoChoice('NONE')
          	teamDomain(null)
          	authToken(null)
        }
	}
}
