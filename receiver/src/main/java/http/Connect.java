package http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connect {
	
	private HttpURLConnection con;
	
	public String get(String uri) //method "get"
	{
		String result=null;
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
}
