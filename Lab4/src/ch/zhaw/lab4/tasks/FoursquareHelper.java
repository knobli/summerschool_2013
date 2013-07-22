package ch.zhaw.lab4.tasks;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import ch.zhaw.lab4.ApiException;

public class FoursquareHelper {
	
	private static final String REST_API_URL = "https://api.foursquare.com/v2/venues/";
	private static final String CLIENT_ID = "NT02XMM3MFLORPTHYVOUT0X2L4JPNJWW5ETD3AWFJ015EQ11";
	private static final String CLIENT_SECRET = "RF0DIM3SBPVUT5TMNQFDWA2JNSXET0XCKL1UFGLIAZ4J3Z44";
	private static final String SECURE_PARAMETERS = "&client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET;

	private static byte[] buff = new byte[1024];
	
	protected static synchronized String downloadDataFromServer(String... params) throws ApiException {
		String returnValue = null;
		String location = params[0];
		String locationTag = "search?ll=" + location;
		//locationTag = "search?ll=40.7,-74";
		String date = "&v=20130718";
		String url = REST_API_URL + locationTag + SECURE_PARAMETERS + date;
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		
		try {
			HttpResponse response = client.execute(request);
			StatusLine status = response.getStatusLine();
			if (status.getStatusCode() != HttpStatus.SC_OK){
				throw new ApiException("Invalid response from foursquare: " + status.toString());
			}
			
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			ByteArrayOutputStream content = new ByteArrayOutputStream();
			
			int readCount = 0;
			while ((readCount = is.read(buff)) != -1){
				content.write(buff,0,readCount);
			}
			returnValue = new String(content.toByteArray());
			
		} catch (ApiException apiE){
			throw apiE;
		} catch (Exception e){
			throw new ApiException("Problem connectiong to the server: " + e.getMessage(), e);
		}
		return returnValue;
	}
}
