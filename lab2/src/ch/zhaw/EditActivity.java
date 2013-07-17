package ch.zhaw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {

	private EditText name;
	private EditText address;
	private EditText state;
	private EditText country;
	private EditText phone;
	private Button doneButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        
        this.name = (EditText) this.findViewById(R.id.textView1);
        this.address = (EditText) this.findViewById(R.id.textView1);
        this.state = (EditText) this.findViewById(R.id.textView1);
        this.country = (EditText) this.findViewById(R.id.textView1);
        this.phone = (EditText) this.findViewById(R.id.textView1);
        
        this.doneButton = (Button) this.findViewById(R.id.editButton);
        
        this.doneButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("name", name.getText().toString());
				setResult(Activity.RESULT_OK, intent);
				finished();
			}
		})
    }
	
}
