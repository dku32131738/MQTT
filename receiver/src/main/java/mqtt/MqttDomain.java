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
	private JsonArray gps;
	private String time;
	
	//객체 생성
	public MqttDomain (String message)
	{
		JsonParser parser=new JsonParser();
		JsonElement result=parser.parse(message);
		convertMessage(result.getAsJsonObject());
	}
	
	//message를 이용하여 id,gps,time 생성 (미완성)
	public void convertMessage(JsonObject message) {
		String type=message.get("type").getAsString();
		JsonObject properties=message.get("properties").getAsJsonObject();
		JsonObject geometry=message.get("geometry").getAsJsonObject();
		this.id=properties.get("id").getAsString();
		this.time=properties.get("time").getAsString();
		this.gps=geometry.get("coordinates").getAsJsonArray();
		System.out.println("test : "+message);
	}
	
	//SendHttp를 사용하여 데이터를 서버로 전송
	public void messageOfHttp() {
		SendHttp sendHttp=new SendHttp(this.id,this.gps,this.time);
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
