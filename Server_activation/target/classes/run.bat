start rmiregistry 9999
timeout 5
start rmid -J-Djava.security.policy=policy/policy.policy
timeout 5
java -Djava.security.policy=policy/policy.policy ui.Server_ac
pause