package ch.zhaw.lab4;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import ch.zhaw.lab4.adapters.VenueAdapter;
import ch.zhaw.lab4.model.Venue;
import ch.zhaw.lab4.tasks.FoursquareAPITask;
import ch.zhaw.lab4.tasks.FoursquareIconTask;

import com.google.android.gms.maps.model.LatLng;

public class NearbyFragment extends ListFragment {
    
	static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
	
	private List<Venue> venues;
	private ListView venueList;
	private LayoutInflater layoutInflater;
	private FoursquareIconTask imgFetcher;
	
	
	public NearbyFragment() {
    	super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.nearby,container, false);
		this.layoutInflater = inflater;
		this.venueList = (ListView) view.findViewById(android.R.id.list);
		this.imgFetcher = new FoursquareIconTask(getActivity());
		
		
		LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		LatLng coordinates;
		if (location != null){
			double lat = (double) location.getLatitude();
			double lon = (double) location.getLongitude();
			coordinates = new LatLng(lat, lon);
		} else {
			Toast.makeText(getActivity(), "No location found: Use default location", Toast.LENGTH_LONG).show();
			coordinates = MELBOURNE;
		}
		FoursquareAPITask fsTask = new FoursquareAPITask(this);
		try{
			fsTask.execute(((Double)coordinates.latitude).toString() + "," + ((Double)coordinates.longitude).toString());
		} catch (Exception e){
			fsTask.cancel(true);
			alert("No Tracks");
		}
		
//		final Object[] data = (Object[]) getLastNonConfigurationInstance();
//		if(data != null){
//			this.venues = (ArrayList<Venue>) data[0];
//			this.imgFetcher = (FoursquareIconTask) data[1];
//			venueList.setAdapter(new VenueAdapter(this,this.imgFetcher,this.layoutInflater,this.venues));
//		}
		
		return view;
	}
	
	public void createList(){
		
	}
	
	public void alert(String msg){
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
	
	public void setVenues(List<Venue> venues){
		this.venues = venues;
		VenueAdapter adapter = new VenueAdapter(this,this.imgFetcher,this.layoutInflater,this.venues);
		this.venueList.setAdapter(adapter);
	}
}
