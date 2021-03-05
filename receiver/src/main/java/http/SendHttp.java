package http;

//서버로 http를 전송
public class SendHttp {

	private String server="http://127.0.0.1:8080/mqtt/mqtt?";
	private String url;
	private String message;
	
	public SendHttp(String id,String gps,String time) {
		String parameter="id="+id+"&gps="+gps+"&time="+time;
		this.url=this.server+parameter;
		System.out.println(this.url);
		this.message=new Connect().get(this.url);
	}
	
	//서버 response반환
	public String getMessage()
	{
		return this.message;
	}
}
