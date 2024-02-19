package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.PublishMsg;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");

		// create a client object and use it to
		Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		
		// - connect to the broker - use "display" as the username
		client.connect();

		// - create the temperature topic on the broker
		client.createTopic("temperature");

		// - subscribe to the topic
		client.subscribe("temperature");

		// - receive messages on the topic
		for (int i = 0; i < 10; i++) {
			PublishMsg message = (PublishMsg) client.receive();
			System.out.println ("Temperature of the day: " + message.getMessage());
		}

		// - unsubscribe from the topic
		client.unsubscribe("temperature");

		// - disconnect from the broker
		client.disconnect();
		System.out.println("Display stopping ... ");
	}
}
