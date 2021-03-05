package main;

import mqtt.ReceiveMqtt;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] topics= {"rider","client"};
		ReceiveMqtt receiveMqtt=new ReceiveMqtt("receiver");
		receiveMqtt.listen(1800, topics);
	}

}
