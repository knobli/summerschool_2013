package ch.zhaw.lab4.adapters;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ch.zhaw.lab4.NearbyFragment;
import ch.zhaw.lab4.R;
import ch.zhaw.lab4.model.Venue;
import ch.zhaw.lab4.tasks.FoursquareIconTask;

public class VenueAdapter extends BaseAdapter implements OnClickListener {
	
	private NearbyFragment fragment;
	private FoursquareIconTask iconTask;
	private LayoutInflater layoutInflater;
	private List<Venue> venues;
	

	public VenueAdapter(NearbyFragment fragment, FoursquareIconTask iconTask,
			LayoutInflater layoutInflater, List<Venue> venues) {
		super();
		this.fragment = fragment;
		this.iconTask = iconTask;
		this.layoutInflater = layoutInflater;
		this.venues = venues;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}
	
	@Override
	public int getCount() {
		return this.venues.size();
	}

	@Override
	public Object getItem(int pos) {
		return this.venues.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		MyViewHolder holder;
		if(convertView == null){
			convertView = layoutInflater.inflate(R.layout.nearby_item, parent, false);
			holder = new MyViewHolder();
			holder.venueName = (TextView) convertView.findViewById(R.id.nameVenue);
			holder.venueAddress = (TextView) convertView.findViewById(R.id.venueAddress);
			holder.catergoryIcon = (ImageView) convertView.findViewById(R.id.imageCategory);
			convertView.setTag(holder);
		} else {
			holder = (MyViewHolder) convertView.getTag();
		}
		
		convertView.setOnClickListener(this);
		Venue venue = venues.get(pos);
		holder.venue = venue;
		holder.venueName.setText(venue.getName());
		String location = "";
//		if (venue.getAddress() != null){
//			location += venue.getAddress();
//		}
		if (venue.getCity() != null){
			if(location.equals("")){
				location += venue.getCity();
			} else {
				location += ", " + venue.getCity();
			}
		}
		if (venue.getState() != null){
			if(location.equals("")){
				location += venue.getState();
			} else {
				location += ", " + venue.getState();
			}
		}		
		if (location != null && !location.equals("")){
			holder.venueAddress.setText(location);
		}
		if(venue.getCategory().getPictureUrl() != null){
			holder.catergoryIcon.setTag(venue.getCategory().getPictureUrl());
			Drawable dr = iconTask.loadImage(this,holder.catergoryIcon);
			if (dr != null){
				holder.catergoryIcon.setImageDrawable(dr);
			}
		} else {
			holder.catergoryIcon.setImageResource(R.drawable.nopicture);
		}
		return convertView;
	}

	@Override
	public void onClick(View v) {
		MyViewHolder holder = (MyViewHolder) v.getTag();
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(holder.venue.getVenueUrl().replace("/v/", "/mobile/v/")));
		this.fragment.getActivity().startActivity(intent);
		
	}

	private static class MyViewHolder {
		public TextView venueName;
		public TextView venueAddress;
		public ImageView catergoryIcon;
		public Venue venue;
	}
	
	

}
