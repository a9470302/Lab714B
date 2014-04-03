package com.example.a_0317_clipboard;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {
	EditText ET;
	Button B;
	TextView TV;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ET = (EditText)findViewById(R.id.editText1);
		B = (Button)findViewById(R.id.button1);
		TV = (TextView)findViewById(R.id.textView1);
		final ClipboardManager CM = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		B.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ClipData C = ClipData.newPlainText("text",ET.getText());
				CM.setPrimaryClip(C);
				TV.setText(get(CM));
			}
		});
	}
	
	private String get(ClipboardManager CM){
		ClipData.Item CI = CM.getPrimaryClip().getItemAt(0);
		return CI.getText().toString();
	}
}
