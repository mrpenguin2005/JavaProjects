/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package camelinaction;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class DataWithCamelJms {

	public static void main(String args[]) throws Exception {
		// create CamelContext
		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.40.10:61616");

		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		// add our route to the CamelContext
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("direct:processoId").to("jms:queue:processoControle-tes");
			}
		});

		// start the route and let it do its work
		context.start();
		System.err.println("Context Started");
		ProducerTemplate template = context.createProducerTemplate();
		String[] processos = { 
				"2811:271451",
				"2652:271452",
				"3550:271453",
				"1626:271450",
				"3491:271416",
				"4380:271415",
				"1034:271414",
				"4492:271413"
				};
		waitSeconds(10);
		for (int i = 0 ; i < processos.length ; i++) {
			template.sendBody("direct:processoId",processos[i]);
		}
//		for (int i = 0 ; i < processos.length ; i++) {
//			template.sendBody("direct:processoId",processos[i]);
//		}
		//Thread.sleep(18000000);

		// stop the CamelContext
		context.stop();
	}
	
	public static void waitSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			return;
		}
	}
}
