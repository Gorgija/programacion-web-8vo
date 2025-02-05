package py.com.pg.cmd;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import py.com.webstock.ejb.services.PagoServiceImpl;
import py.com.webstock.ejb.services.local.PagoServiceRemote;

public class EJBHelper {

	public static PagoServiceRemote lookupRemotePagoService()
			throws NamingException {
		Properties p = new Properties();
		p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		// jndiProperties.put("java.naming.factory.initial",
		// "org.jnp.interfaces.NamingContextFactory");
		final Context context = new InitialContext(p);
		// The app name is the application name of the deployed EJBs. This is
		// typically the ear name
		// without the .ear suffix. However, the application name could be
		// overridden in the application.xml of the
		// EJB deployment on the server.
		// Since we haven't deployed the application as a .ear, the app name for
		// us will be an empty string
		final String appName = "WebStock";
		// This is the module name of the deployed EJBs on the server. This is
		// typically the jar name of the
		// EJB deployment, without the .jar suffix, but can be overridden via
		// the ejb-jar.xml
		// In this example, we have deployed the EJBs in a
		// jboss-as-ejb-remote-app.jar, so the module name is
		// jboss-as-ejb-remote-app
		final String moduleName = "WebStockEJB";
		// AS7 allows each deployment to have an (optional) distinct name. We
		// haven't specified a distinct name for
		// our EJB deployment, so this is an empty string
		final String distinctName = "";
		// The EJB name which by default is the simple class name of the bean
		// implementation class
		final String beanName = PagoServiceImpl.class.getSimpleName();
		// the remote view fully qualified class name
		final String viewClassName = PagoServiceRemote.class.getName();
		// let's do the lookup
		String aBuscar = "ejb:" + appName + "/" + moduleName + "/"
				+ distinctName + "/" + beanName + "!" + viewClassName;
		aBuscar = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!"
				+ viewClassName;
		System.out.println(aBuscar);
		Object o = context.lookup(aBuscar);
		System.out.println(o.getClass());
		return (PagoServiceRemote) o;
	}

}
