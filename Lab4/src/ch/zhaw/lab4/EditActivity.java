package ch.zhaw.lab4;

import java.util.HashMap;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ch.zhaw.lab4.database.MeDataSource;
import ch.zhaw.lab4.model.Profil;

public class EditActivity extends Activity {

	private Button saveButton; 
	private Button cancelButton;
	private EditText nameField;
	private EditText addressField;
	private EditText cityField;
	private EditText stateField;
	private EditText phoneField;
	
	private Profil profil;
	private boolean newFlag;
	private MeDataSource datasource;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        this.nameField = (EditText)this.findViewById(R.id.name);
        this.addressField = (EditText)this.findViewById(R.id.address);
        this.cityField = (EditText)this.findViewById(R.id.city);
        this.stateField = (EditText)this.findViewById(R.id.state);
        this.phoneField = (EditText)this.findViewById(R.id.phone);
        
        this.saveButton = (Button) this.findViewById(R.id.saveButton);
        this.cancelButton = (Button) this.findViewById(R.id.cancelButton);
        
        datasource = new MeDataSource(this);
        datasource.open();
        
        profil = datasource.getProfil();
		if(profil == null){
        	profil = new Profil();
        	newFlag = true;
        } else {
        	nameField.setText(profil.getName());
        	addressField.setText(profil.getAddress());
        	cityField.setText(profil.getCity());
        	stateField.setText(profil.getState());
        	phoneField.setText(profil.getPhone());
        	newFlag = false;
        } 
        
        this.saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ComponentName callingActivity = getCallingActivity();
				HashMap<Integer, String> valueMap = new HashMap<Integer, String>();
				
				String nameString = nameField.getText().toString();
				if(!profil.getName().equals(nameString)){
			        valueMap.put(R.id.name, nameString);
			        profil.setName(nameString);
				}
		        
		        String addressString = addressField.getText().toString();
		        if(!profil.getAddress().equals(addressString)){
			        valueMap.put(R.id.address, addressString);
			        profil.setAddress(addressString);
		        }
		        
		        String cityString = cityField.getText().toString();
		        if(!profil.getCity().equals(cityString)){
			        valueMap.put(R.id.city, cityString);
			        profil.setCity(cityString);
		        }
		        
		        String stateString = stateField.getText().toString();
		        if(!profil.getState().equals(stateString)){
			        valueMap.put(R.id.state, stateString);
			        profil.setState(stateString);
		        }
		        
		        String phoneString = phoneField.getText().toString();
		        if(!profil.getPhone().equals(phoneString)){
		        	valueMap.put(R.id.phone, phoneString);
		        	profil.setPhone(phoneString);
		        }
		        
		        if(newFlag){
		        	datasource.createProfil(profil);
		        } else {
		        	datasource.updateProfil(profil);
		        }
		        
				Intent intent = new Intent(EditActivity.this,callingActivity.getClass());
				for( Entry<Integer, String> value : valueMap.entrySet() ){
					if (!value.getValue().equals("")){
						intent.putExtra(value.getKey().toString(), value.getValue());
					}
				}
				
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});
        
        this.cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    }
	
}
