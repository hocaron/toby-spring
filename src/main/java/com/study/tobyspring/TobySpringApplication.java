package com.study.tobyspring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.study.tobyspring.ping.PingController;
import com.study.tobyspring.ping.SimplePingService;

@SpringBootApplication
public class TobySpringApplication {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(PingController.class);
		applicationContext.registerBean(SimplePingService.class);
		applicationContext.refresh();

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet(
				"frontController",
				new HttpServlet() {
					@Override
					public void service(HttpServletRequest req, HttpServletResponse res) throws
						ServletException,
						IOException {
						// 공통 처리
						if (req.getRequestURI().equals("/ping") && req.getMethod().equals(HttpMethod.GET.name())) {
							String name = req.getParameter("name");

							PingController pingController = applicationContext.getBean(PingController.class);
							String body = pingController.ping(name);

							res.setContentType(MediaType.TEXT_PLAIN_VALUE);
							res.getWriter().println(body);
						} else {
							res.setStatus(HttpStatus.NOT_FOUND.value());
						}
					}
				}).addMapping("/*");
		});
		webServer.start();
	}
}
