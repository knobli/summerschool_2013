package ch.zhaw;

import java.util.HashMap;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {

	private Button saveButton; 

	private Button cancelButton;

	private EditText nameField;

	private EditText addressField;

	private EditText cityField;

	private EditText stateField;

	private EditText phoneField;
	
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
        
        this.saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HashMap<Integer, String> valueMap = new HashMap<Integer, String>();
		        valueMap.put(R.id.name, nameField.getText().toString());
		        valueMap.put(R.id.address, addressField.getText().toString());
		        valueMap.put(R.id.city, cityField.getText().toString());
		        valueMap.put(R.id.state, stateField.getText().toString());
		        valueMap.put(R.id.phone, phoneField.getText().toString());
		        
				Intent intent = new Intent(EditActivity.this,MyActivity.class);
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
