package ch.zhaw.lab4;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    Spinner spinnerctrl;
    Button btn;
    Locale myLocale;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        spinnerctrl = (Spinner) findViewById(R.id.spinner1);
        spinnerctrl.setOnItemSelectedListener(new OnItemSelectedListener() {
 
            public void onItemSelected(AdapterView<?> parent, View view,
                    int pos, long id) {
 
                if (pos == 1) {
                    Toast.makeText(parent.getContext(),"You have selected German", Toast.LENGTH_SHORT).show();
                    setLocale("de");
                } else if (pos == 2) {
                    Toast.makeText(parent.getContext(),"You have selected Spanish", Toast.LENGTH_SHORT).show();
                    setLocale("es");
                } else if (pos == 3) {
                    Toast.makeText(parent.getContext(),"You have selected English", Toast.LENGTH_SHORT).show();
                    setLocale("en");
                }
 
            }
 
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
 
        });
    }	
	
	public void setLocale(String lang) { 
		Locale myLocale = new Locale(lang); 
		Resources res = getResources(); 
		DisplayMetrics dm = res.getDisplayMetrics(); 
		Configuration conf = res.getConfiguration(); 
		conf.locale = myLocale; 
		res.updateConfiguration(conf, dm); 
		Intent startActivity = new Intent(this, MainActivity.class); 
		startActivity(startActivity); 
	} 	
	
}
