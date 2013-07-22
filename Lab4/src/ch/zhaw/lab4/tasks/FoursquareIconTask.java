package ch.zhaw.lab4.tasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class FoursquareIconTask {

	private HashMap<String, Drawable> imageCache;
	private static final Drawable DEFAULT_ICON = null;
	private BaseAdapter adapt;
	
	public FoursquareIconTask(Context ctx) {
		imageCache = new HashMap<String, Drawable>();
	}
	
	public Drawable loadImage(BaseAdapter adapt, ImageView view){
		this.adapt = adapt;
		String url = (String) view.getTag();
		if(imageCache.containsKey(url)){
			return imageCache.get(url);
		} else {
			new ImageTask().execute(url);
			return DEFAULT_ICON;
		}
	}
	
	private class ImageTask  extends AsyncTask<String, Void, Drawable>{
		private String picUrl;
		
		@Override
		protected Drawable doInBackground(String... params) {
			picUrl = params[0];
			InputStream is;
			try {
				URL url = new URL(picUrl);
				is = url.openStream();
			} catch (MalformedURLException e){
				throw new RuntimeException(e);
			} catch (IOException e){
				throw new RuntimeException(e);
			}
			return Drawable.createFromStream(is, "src");
		}
		
		@Override
		protected void onPostExecute(Drawable result) {
			super.onPostExecute(result);
			synchronized (this) {
				imageCache.put(picUrl, result);
			}
			adapt.notifyDataSetChanged();
		}
		
	}

}
