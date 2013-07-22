package ch.zhaw.lab4;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ch.zhaw.lab4.database.MeDataSource;
import ch.zhaw.lab4.model.Profil;

public class MeFragment extends Fragment
{
    public static final int REQUEST_TEXT = 0;
    
    public MeFragment() {
    	super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.main, container, false);
		
		MeDataSource datasource = new MeDataSource(getActivity());
        datasource.open();
        
        Profil profil = datasource.getProfil();
		if(profil != null){
			TextView nameField = (TextView)rootView.findViewById(R.id.name);
			TextView addressField = (TextView)rootView.findViewById(R.id.address);
			TextView cityField = (TextView)rootView.findViewById(R.id.city);
			TextView stateField = (TextView)rootView.findViewById(R.id.state);
			TextView phoneField = (TextView)rootView.findViewById(R.id.phone);			
        	nameField.setText(profil.getName());
        	addressField.setText(profil.getAddress());
        	cityField.setText(profil.getCity());
        	stateField.setText(profil.getState());
        	phoneField.setText(profil.getPhone());
		}
        
        Button editButton = (Button) rootView.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), EditActivity.class);
				startActivityForResult(intent, REQUEST_TEXT);
			}
		});		
		return rootView;
	}
	
	 @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	super.onActivityResult(requestCode, resultCode, data);
	    	if(requestCode == MeFragment.REQUEST_TEXT){
	    		if(resultCode == Activity.RESULT_OK){
	    			String msg;
	    			Resources res = getResources();
	    			Bundle bundle = data.getExtras();
	    			if(bundle != null){
	    				int numberOfChanges = bundle.size();
		    			for(String key : bundle.keySet()){
		    				String value = bundle.getString(key);
		    				((TextView) getActivity().findViewById(Integer.parseInt(key))).setText(value);
		    			}
		    			//String text = String.format(res.getString(R.string.welcome_messages),username, mailCount);
		    			msg = res.getQuantityString(R.plurals.save_data, numberOfChanges, numberOfChanges);
	    			} else {
	    				msg = res.getString(R.string.no_save_data);
	    			}
	    			Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
	    		}
	    	}
	 }
    
}
