package http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connect {
	
	private HttpURLConnection con;
	String result;
	
	public String get(String uri) //method "get"
	{
		try {
			URL url=new URL(uri);
			con=(HttpURLConnection)url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestMethod("GET");
			
			con.setDoOutput(false);
			StringBuilder sb=new StringBuilder();
			if(con.getResponseCode()<300) {
				BufferedReader br=new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				String line;
				while((line=br.readLine())!=null) {
					sb.append(line).append("\n");
				}
				br.close();
				result=""+sb.toString();
				con.disconnect();
				return result;
			}else {
				result="{}";
				con.disconnect();
				return result; //if success
			}
		}catch(Exception e) {
			System.err.println(e.toString());
			result="{}";
			con.disconnect();
			return result; //if fail
		}
	}
	public boolean post(String uri,String message) //method post
	{
		try {
			URL url=new URL(uri);
			HttpURLConnection con=(HttpURLConnection)url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setDefaultUseCaches(false);
			
			OutputStreamWriter wr=new OutputStreamWriter(con.getOutputStream());
			wr.write(message);
			wr.flush();
			
			StringBuilder sb=new StringBuilder();
			if(con.getResponseCode()<300) {
				BufferedReader br=new BufferedReader(
						new InputStreamReader(con.getInputStream(),"utf-8"));
				String line;
				while((line=br.readLine())!=null) {
					sb.append(line).append("\n");
				}
				br.close();
				System.out.println(""+sb.toString());
				this.result=""+sb.toString();
				con.disconnect();
				return true;
			}else {
				System.out.println(con.getResponseMessage());
				this.result="{}";
				con.disconnect();
				return false;
			}
		}catch(Exception e) {
			System.err.println(e.toString());
			this.result="{}";
			con.disconnect();
			return false;
		}
	}
}
