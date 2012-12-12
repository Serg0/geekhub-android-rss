package com.example.geekhub.geekhub_android_rss;

import android.os.Bundle;
import android.view.View;
import org.holoeverywhere.app.Activity;
import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.EditText;

public class BaseActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final EditText edit = (EditText) findViewById(R.id.editText);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(BaseActivity.this);
                dialog.setTitle("Dialog title");
                dialog.setMessage(edit.getText());
                dialog.setPositiveButton("OK", null);
                dialog.show();
            }
        });

//        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
