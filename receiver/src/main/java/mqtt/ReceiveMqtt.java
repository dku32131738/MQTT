package mqtt;

import java.util.HashMap;
import java.util.function.Consumer;

//mqtt瑜� �닔�떊諛쏅뒗 �겢�옒�뒪,�꽕�젙 �닔�뻾
public class ReceiveMqtt{

	private String broker= "tcp://192.168.0.146:1883";
	private String clientId;
	
	//媛앹껜 �깮�꽦 諛� id �꽕�젙
	public ReceiveMqtt(String clientId) {
		this.clientId=clientId;
	}
	
	//Client 媛앹껜瑜� �씠�슜�븯�뿬 listen
	public String listen(int sec,String[] topics) {
        final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //硫붿떆吏�瑜� 諛쏅뒗 肄쒕갚 �뻾�쐞
            arg.forEach((key, value)->{
            	if(key=="message") {
            		MqttDomain mqttDomain=new MqttDomain(value.toString());
            		mqttDomain.messageOfHttp();
            	}
                System.out.println( String.format("messeage arrived : �key -> %s, value -> %s", key, value) );
            });            
        };
        Client client = new Client(pdk);  //�빐�떦 �븿�닔瑜� �깮�꽦�옄濡� �꽔�뼱以��떎.
        client.init(broker,clientId)
              .subscribe(topics);  //subscribe 硫붿냼�뱶�뒗 援щ룆�븷 ���긽
        client.initConnectionLost( (arg)->{  //肄쒕갚�뻾�쐞1, �꽌踰꾩��쓽 �뿰寃곗씠 �걡湲곕㈃ �룞�옉
            arg.forEach((key, value)->{
                System.out.println( String.format("session closed key -> %s, value -> %s", key, value) );
            });
        });
        client.initDeliveryComplete((arg)-> {  //肄쒕갚�뻾�쐞2, 硫붿떆吏�瑜� �쟾�넚�븳 �씠�썑 �룞�옉
            arg.forEach((key, value)->{
                System.out.println( String.format("complete to send message-> %s, 媛� -> %s", key, value) );
            });
        });
        
        new Thread( ()->{
            try {
                Thread.sleep(1000*sec);
                System.out.println("close");
                client.close();  //醫낅즺�뒗 �씠�젃寃�!
            } catch (Exception e) {
                e.printStackTrace();
            }
        } ).start();   
		return "String";
	}
}
