package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nt.service.IPurchaseOrder;

@SpringBootApplication
public class SpringSecurityProj04MailApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringSecurityProj04MailApplication.class, args);
		IPurchaseOrder order = ctx.getBean("purchaseService", IPurchaseOrder.class);
		try {
			order.purchase(new String[] { "shirt", "trouser", "watch" }, new double[] { 5000, 4000, 3000 },
					new String[] { "lokesh484ee@gmail.com", "sanjaybannigol65@gmail.com",
							 });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
