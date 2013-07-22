package ch.zhaw.lab4.tasks;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import ch.zhaw.lab4.ApiException;
import ch.zhaw.lab4.NearbyFragment;
import ch.zhaw.lab4.R;
import ch.zhaw.lab4.model.Category;
import ch.zhaw.lab4.model.Venue;

public class FoursquareAPITask extends AsyncTask<String, Integer, String> {
	
	private ProgressDialog progress;
	private Context context;
	private NearbyFragment fragment;
	
	public FoursquareAPITask(NearbyFragment fragment) {
		super();
		this.fragment = fragment;
		this.context = this.fragment.getActivity().getApplicationContext();
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progress = ProgressDialog.show(this.fragment.getActivity(), "Searching ...", this.context.getResources().getString(R.string.looking_for_results),true, false);
	}
	
	@Override
	protected String doInBackground(String... params) {
		String res = new String();
		try{
			res = FoursquareHelper.downloadDataFromServer(params);
		} catch (Exception e){
			new ApiException("Could not get JSON data");
		}
		return res;
	}
	
	
	@Override
	protected void onPostExecute(String result) {
		ArrayList<Venue> venues = new ArrayList<Venue>();
		progress.dismiss();
		
		if (result.length() == 0){
			this.fragment.alert("Unable to find venues. Please try later");
			return;
		}
		
		try{
			JSONObject respObj = new JSONObject(result);
			JSONObject responseObj = respObj.getJSONObject("response");
			JSONArray venuesArray = responseObj.getJSONArray("venues");
			for(int i = 0; i < venuesArray.length(); i++){
				JSONObject venue = venuesArray.getJSONObject(i);
				String name = venue.getString("name");
				String venueUrl = venue.getString("canonicalUrl");
				JSONObject location =  venue.getJSONObject("location");
				String address = null;
				String city = null;
				String state = null;
				if (location != null){
					if(!location.isNull("address")){
						address = location.getString("address");
					}
					if(!location.isNull("city")){
						city = location.getString("city");
					}
					if(!location.isNull("state")){
						state = location.getString("state");
					}
				}
				
				JSONArray categories =  venue.getJSONArray("categories");
				
				String categoryName = null;
				String categoryImageUrl = null;
				if (categories.length() > 0){
					JSONObject category = categories.getJSONObject(0);
					categoryName = category.getString("name");
					JSONObject icon =  category.getJSONObject("icon");
					if (icon != null){
						categoryImageUrl = "";
						categoryImageUrl += icon.getString("prefix");
						categoryImageUrl += "bg_88";
						categoryImageUrl += icon.getString("suffix");
					} else {
						categoryImageUrl = null;
					}
				} else {
					categoryName = "No catergorie";
					categoryImageUrl = null;
				}
				venues.add(new Venue(name, address, city, state, venueUrl, new Category(categoryName, categoryImageUrl)));
			}
		} catch (JSONException e){
			throw new ApiException("JSON parsing failed", e);
		}
		
		this.fragment.setVenues(venues);
	}

}
