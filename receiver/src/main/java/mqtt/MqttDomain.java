package mqtt;

import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import http.SendHttp;

public class MqttDomain {
	
	private String id;
	private String gps;
	private String time;
	
	//객체 생성
	public MqttDomain (String message)
	{
		convertMessage(message);
	}
	
	//message를 이용하여 id,gps,time 생성 (미완성)
	public void convertMessage(String message) {
		System.out.println(message);
		Gson gson=new Gson();
		Rider rider=gson.fromJson(message, Rider.class);
		this.id=rider.getId();
		this.gps=rider.getGps();
		this.time=rider.getTime();
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
