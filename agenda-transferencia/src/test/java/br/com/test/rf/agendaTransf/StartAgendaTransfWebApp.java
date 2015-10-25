/**
 * 
 */
package br.com.test.rf.agendaTransf;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.startup.Tomcat;

/**
 * @author "davidson.rodrigues"
 *
 * @created 23 de out de 2015
 */
public class StartAgendaTransfWebApp {
	
	private static final int PORT = 8888;

	private static final String WEB_APP_DIR = "src/main/webapp/";

	public static void main(String[] args) {
        try {
        	Tomcat tomcat = new Tomcat();
        	tomcat.setPort(PORT);
			tomcat.addWebapp("/", new File(WEB_APP_DIR).getAbsolutePath());
			System.out.println("configuring app with basedir: " + new File("./" + WEB_APP_DIR).getAbsolutePath());
			
			System.out.println(">>>>>>> Startando a aplicação...");
			tomcat.start();
			System.out.println(">>>>>>> Aplicação startada! <<<<<<<<");
			tomcat.getServer().await();
		} catch (Exception e) {
			System.out.println("Não foi possível subir a aplicação.\nErro: " + e.getMessage());
		}
	}

}
