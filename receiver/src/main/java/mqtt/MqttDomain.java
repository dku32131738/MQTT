package mqtt;

import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

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
		String type=message.get("type").getAsString();
		JsonObject properties=message.get("properties").getAsJsonObject();
		JsonObject geometry=message.get("geometry").getAsJsonObject();
		this.id=properties.get("id").getAsString();
		this.time=properties.get("time").getAsString();
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
	
	class Rider{
		private String id;
		private String gps;
		private String time;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getGps() {
			return gps;
		}
		public void setGps(String gps) {
			this.gps = gps;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
	}
}
