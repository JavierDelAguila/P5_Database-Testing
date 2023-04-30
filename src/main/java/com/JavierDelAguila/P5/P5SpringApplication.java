
package com.JavierDelAguila.P5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;





@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.JavierDelAguila.P5"})
public class P5SpringApplication extends SpringApplication{
	
	//@Autowired
	//LocationServiceImpl locationService;

	//LoadCSVToDatabase load = new LoadCSVToDatabase(locationService)


	public static void main(String[] args) {

		

		P5SpringApplication.run(P5SpringApplication.class, args);

        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // con esto obtenemos los beans configurados en el archivo XML
		
		/*
		
		<!--

<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mydatabase"/>
    <property name="username" value="root"/>
    <property name="password" value="password"/>
</bean>

-->
		*/

	}

}
