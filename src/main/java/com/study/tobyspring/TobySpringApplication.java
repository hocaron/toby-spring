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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootApplication
public class TobySpringApplication {

	public static void main(String[] args) {
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			PingController pingController = new PingController();
			servletContext.addServlet(
				"frontController",
				new HttpServlet() {
					@Override
					public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
						// 공통 처리
						if (req.getRequestURI().equals("/ping") && req.getMethod().equals(HttpMethod.GET.name())) {
							String name = req.getParameter("name");

							String body = pingController.ping(name);

							res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
							res.getWriter().println(body);
						} else if (req.getRequestURI().equals("/user")) {

						} else {
							res.setStatus(HttpStatus.NOT_FOUND.value());
						}
					}
				}).addMapping("/*");
		});
		webServer.start();
	}

}
