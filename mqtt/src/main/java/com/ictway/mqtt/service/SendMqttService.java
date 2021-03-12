package com.ictway.mqtt.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.ictway.mqtt.domain.RiderDomain;

public class SendMqttService {
	
	private String topic;
	private String content;
	private String broker;
	private String clientID;
	private MemoryPersistence persistence;
	
	private static final Logger logger = LoggerFactory.getLogger(SendMqttService.class);
	
	//service 객체 생성 및 초기화
	public SendMqttService(String topic,String content) {
		this.broker="tcp://192.168.0.146:1883";
		this.clientID="system";
		this.topic=topic;
		this.content=content;
		this.persistence=new MemoryPersistence();
	}
	
	public SendMqttService(String topic,RiderDomain riderDomain) {
		this.broker="tcp://192.168.0.146:1883";
		this.clientID="system";
		this.topic=topic;
		Gson gson=new Gson();
		this.content=gson.toJson(riderDomain).toString();
		this.persistence=new MemoryPersistence();
		logger.info(content);
	}

	//mqtt전송
	public boolean sendMqtt(int qos) {
		boolean flag;
        try {
        	logger.info("start sendMqtt");
            MqttClient sampleClient = new MqttClient(broker, this.clientID, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            logger.info("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            logger.info("Connected");
            logger.info("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            logger.info("Message published"+topic);
            sampleClient.disconnect();
            logger.info("Disconnected");
            flag=true;
        } catch(MqttException me) {
        	logger.warn("reason "+me.getReasonCode());
        	logger.warn("msg "+me.getMessage());
        	logger.warn("loc "+me.getLocalizedMessage());
        	logger.warn("cause "+me.getCause());
        	logger.warn("excep "+me);
            me.printStackTrace();
            flag=false;
        }
		return flag;
	}
}