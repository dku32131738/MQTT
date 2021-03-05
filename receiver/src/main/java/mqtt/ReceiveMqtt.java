package mqtt;

import java.util.HashMap;
import java.util.function.Consumer;

//mqtt를 수신받는 클래스,설정 수행
public class ReceiveMqtt{

	private String broker= "tcp://192.168.0.146:1883";
	private String clientId;
	
	//객체 생성 및 id 설정
	public ReceiveMqtt(String clientId) {
		this.clientId=clientId;
	}
	
	//Client 객체를 이용하여 listen
	public String listen(int sec,String[] topics) {
        final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //메시지를 받는 콜백 행위
            arg.forEach((key, value)->{
            	if(key=="message") {
            		MqttDomain mqttDomain=new MqttDomain(value.toString());
            		mqttDomain.messageOfHttp();
            	}
                System.out.println( String.format("메시지 도착 : 키 -> %s, 값 -> %s", key, value) );
            });            
        };
        Client client = new Client(pdk);  //해당 함수를 생성자로 넣어준다.
        client.init(broker,clientId)
              .subscribe(topics);  //subscribe 메소드는 구독할 대상
        client.initConnectionLost( (arg)->{  //콜백행위1, 서버와의 연결이 끊기면 동작
            arg.forEach((key, value)->{
                System.out.println( String.format("커넥션 끊김~! 키 -> %s, 값 -> %s", key, value) );
            });
        });
        client.initDeliveryComplete((arg)-> {  //콜백행위2, 메시지를 전송한 이후 동작
            arg.forEach((key, value)->{
                System.out.println( String.format("메시지 전달 완료~! 키 -> %s, 값 -> %s", key, value) );
            });
        });
        
        new Thread( ()->{
            try {
                Thread.sleep(1000*sec);
                System.out.println("close");
                client.close();  //종료는 이렇게!
            } catch (Exception e) {
                e.printStackTrace();
            }
        } ).start();   
		return "String";
	}
}
