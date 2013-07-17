package ch.zhaw;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity
{
    protected static final int REQUEST_TEXT = 0;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button editButton = (Button) this.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyActivity.this, EditActivity.class);
				startActivityForResult(intent, REQUEST_TEXT);
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	String msg = "Nothing updated";
    	if(requestCode == REQUEST_TEXT){
    		if(resultCode == Activity.RESULT_OK){
    			Bundle bundle = data.getExtras();
    			Resources res = getResources();
    			if(bundle != null){
    				int numberOfChanges = bundle.size();
	    			for(String key : bundle.keySet()){
	    				String value = bundle.getString(key);
	    				((TextView) this.findViewById(Integer.parseInt(key))).setText(value);
	    			}
	    			msg = res.getQuantityString(R.plurals.save_data, numberOfChanges, numberOfChanges);
    			} else {
    				msg = res.getString(R.string.no_save_data);
    			}
    		}
    	}
    	Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
