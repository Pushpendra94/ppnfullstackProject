package com.example.demo;

import org.apache.commons.logging.Log;
//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.*;


@SpringBootApplication
public class PpnfullstackApplication {
 public static final Logger LOGGER=LoggerFactory.getLogger(PpnfullstackApplication.class);
 @Bean
 BCryptPasswordEncoder bCryptPasswordEncoder() {
	 return new BCryptPasswordEncoder();
 }
	public static void main(String[] args) {
SpringApplication.run(PpnfullstackApplication.class, args);
		
	
//	 try (ConfigurableApplicationContext context = 
//	          SpringApplication.run(PpnfullstackApplication.class, args)) {
//	        LOGGER.trace("context: " + context);
//	      }
	}
	//@Override
    void run(String... args) throws Exception {
    	LOGGER.info("Joining thread, you can press Ctrl+C to shutdown application");
        Thread.currentThread().join();
    }
}


