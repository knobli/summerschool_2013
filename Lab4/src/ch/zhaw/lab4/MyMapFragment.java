package ch.zhaw.lab4;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ch.zhaw.lab4.database.MeDataSource;
import ch.zhaw.lab4.model.Profil;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapFragment extends Fragment {
	
	private GoogleMap mMap;
	static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
	private static View view;
	
	public MyMapFragment() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view != null){
			ViewGroup parent = (ViewGroup) view.getParent();
			if(parent != null)
				parent.removeView(view);
		}
		try{
			view = inflater.inflate(R.layout.map_layout,container, false);
		} catch (InflateException e){
			/* map already loaded */
		}
		
		String address;
		
		MeDataSource datasource = new MeDataSource(getActivity());
        datasource.open();
        
        Profil profil = datasource.getProfil();
		if(profil != null){
			address = profil.getAddress() + ", " + profil.getCity() + ", " + profil.getState();
		} else {
			address = getString(R.string.val_address);
			address += ", " + getString(R.string.val_city);
			address += ", " + getString(R.string.val_state);
		}
		Geocoder geocoder = new Geocoder(getActivity());  
		List<Address> addresses;
		LatLng coordinate;
		try {
			addresses = geocoder.getFromLocationName(address, 1);
			if(addresses.size() > 0) {
				double latitude= addresses.get(0).getLatitude();
				double longitude= addresses.get(0).getLongitude();
			    coordinate = new LatLng(latitude, longitude);
			} else {
				Toast.makeText(getActivity(), "Address not found: Use default location", Toast.LENGTH_LONG).show();
				coordinate = MELBOURNE;
				address = "Melbourne";
			}
		} catch (IOException e) {
			Toast.makeText(getActivity(), "Error: Use default location", Toast.LENGTH_LONG).show();
			coordinate = MELBOURNE;
			address = "Melbourne";
		}
			
		SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
		mMap = mapFragment.getMap();
		Marker marker = mMap.addMarker(new MarkerOptions().position(coordinate).title(address));
		marker.showInfoWindow();
		CameraUpdate center=CameraUpdateFactory.newLatLng(coordinate);
		CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);	
		mMap.moveCamera(center);
		mMap.animateCamera(zoom);
		return view;
	}

}
