package com.study.tobyspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.study.tobyspring.ping.PingController;
import com.study.tobyspring.ping.PingService;
import com.study.tobyspring.ping.SimplePingService;

@Configuration
public class TobySpringApplication {
	@Bean
	public PingController pingController(PingService pingService) {
		return new PingController(pingService);
	}

	@Bean
	public PingService pingService() {
		return new SimplePingService();
	}

	public static void main(String[] args) {
		// spring container 생성 -> bean 등록 -> bean 초기화
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
			@Override
			protected void onRefresh() {
				super.onRefresh();

				// servlet container 생성 -> dispatcherServlet 등록 -> dispatcherServlet 초기화
				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet(
						"dispatcherServlet",
						new DispatcherServlet(this)
					).addMapping("/*");
				});
				webServer.start();
			}
		};
		applicationContext.register(TobySpringApplication.class);
		applicationContext.refresh();
	}
}
