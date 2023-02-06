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

@SpringBootApplication
public class TobySpringApplication {

	public static void main(String[] args) {
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet(
				"ping",
				new HttpServlet() {
					@Override
					public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
						res.setStatus(200);
						res.setHeader("Content-Type", "text/plain");
						res.getWriter().println("ping");
					}
				}).addMapping("/ping");
		});
		webServer.start();
	}

}
