import jenkins.security.s2m.AdminWhitelistRule
import jenkins.model.Jenkins

def jenkins = Jenkins.getInstance()

//set master -> slave security
jenkins.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)

//Disable CLI over Remoting
jenkins.getDescriptor("jenkins.CLI").get().setEnabled(false)

//Disable jnlp
jenkins.setSlaveAgentPort(-1);

//Disable old Non-Encrypted protocols ()
HashSet<String> newProtocols = new HashSet<>(jenkins.getAgentProtocols());
newProtocols.removeAll(Arrays.asList(
        "JNLP2-connect", "JNLP-connect", "CLI-connect"   
));
jenkins.setAgentProtocols(newProtocols)

jenkins.save()
