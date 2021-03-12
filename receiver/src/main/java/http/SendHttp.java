package http;

import com.google.gson.JsonArray;

//�꽌踰꾨줈 http瑜� �쟾�넚
public class SendHttp {

	private String server="http://127.0.0.1:8000/mqtt/rider?";
	private String url;
	private String message;
	
	public SendHttp(String id,float latitude,float longitude,String time) {
		String parameter="id="+id+"&latitude="+latitude+"&longitude="+longitude+"&time="+time;
		this.url=this.server+parameter;
		System.out.println(this.url);
		this.message=new Connect().get(this.url);
	}
	
	//�꽌踰� response諛섑솚
	public String getMessage()
	{
		return this.message;
	}
}
