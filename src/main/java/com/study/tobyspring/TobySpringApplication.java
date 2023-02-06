package com.study.tobyspring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.study.tobyspring.ping.PingController;
import com.study.tobyspring.ping.SimplePingService;

@SpringBootApplication
public class TobySpringApplication {

	public static void main(String[] args) {
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		applicationContext.registerBean(PingController.class);
		applicationContext.registerBean(SimplePingService.class);
		applicationContext.refresh();

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet(
				"dispatcherServlet",
				new DispatcherServlet(applicationContext)
				).addMapping("/*");
		});
		webServer.start();
	}
}
