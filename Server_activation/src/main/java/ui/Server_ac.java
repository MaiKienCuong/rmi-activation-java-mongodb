package ui;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupID;
import java.util.Properties;

public class Server_ac {

	public static void main(String[] args) {
		SecurityManager sm = System.getSecurityManager();
		if (sm == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			Properties properties = new Properties();
			properties.setProperty("java.security.policy", "policy/policy.policy");

			ActivationGroupDesc groupDesc = new ActivationGroupDesc(properties, null);

			ActivationGroupID groupID = ActivationGroup.getSystem().registerGroup(groupDesc);

			ActivationDesc desc = new ActivationDesc(groupID, dao.impl.User_dao.class.getName(), null, null);

			ActivationDesc desc2 = new ActivationDesc(groupID, dao.impl.News_dao.class.getName(), null, null);

			Remote register = Activatable.register(desc);
			Remote register2 = Activatable.register(desc2);

			Naming.rebind("rmi://DESKTOP-3FJ9IE1:9999/userdao", register);
			Naming.rebind("rmi://DESKTOP-3FJ9IE1:9999/newsdao", register2);

			System.out.println("=======================================");
			System.out.println("server ok");
			System.out.println("=======================================");

			System.exit(0);

		} catch (RemoteException | ActivationException | MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
