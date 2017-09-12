import jenkins.model.*
import hudson.security.*

//Add new credentials
def jenkinsRealm = new HudsonPrivateSecurityRealm(false)
jenkinsRealm.createAccount("admin", "password")

//Only allow authenticated users
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)

def jenkins = Jenkins.getInstance()
jenkins.setSecurityRealm(jenkinsRealm)
jenkins.setAuthorizationStrategy(strategy)
jenkins.save()
