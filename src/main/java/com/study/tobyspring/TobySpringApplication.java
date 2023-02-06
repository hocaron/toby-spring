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
		// spring container 생성 -> bean 등록 -> bean 초기화
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		applicationContext.registerBean(PingController.class);
		applicationContext.registerBean(SimplePingService.class);
		applicationContext.refresh();

		// servlet container 생성 -> dispatcherServlet 등록 -> dispatcherServlet 초기화
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
