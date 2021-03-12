package mqtt;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import http.SendHttp;

public class MqttDomain {
	
	private String id;
	private float latitude;
	private float longitude;
	private String time;
	
	//생성자 json 생성후 변환
	public MqttDomain (String message)
	{
		JsonParser parser=new JsonParser();
		JsonElement result=parser.parse(message);
		convertMessage(result.getAsJsonObject());
	}
	
	//MQTT 메시지 변환
	public void convertMessage(JsonObject message) {
		JsonObject properties=message.get("properties").getAsJsonObject();
		JsonObject geometry=message.get("geometry").getAsJsonObject();
		this.id=properties.get("id").getAsString();
		this.time=properties.get("time").getAsString();
		this.time=time.replace(" ", "+");
		JsonArray gps=geometry.get("coordinates").getAsJsonArray();
		this.latitude=gps.get(0).getAsFloat();
		this.longitude=gps.get(1).getAsFloat();
		
		System.out.println("test : "+message);
	}
	
	//SendHttp瑜� �궗�슜�븯�뿬 �뜲�씠�꽣瑜� �꽌踰꾨줈 �쟾�넚
	public void messageOfHttp() {
		SendHttp sendHttp=new SendHttp(this.id,this.latitude,this.longitude,this.time);
		System.out.println(sendHttp.getMessage());
	}
	
}
