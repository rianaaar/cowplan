package com.psbo.cowplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class input_date extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_date);
    rg = (RadioGroup) findViewById(R.id.rgroup);

    }
}

public void rbclick(View v){
    int radiobuttonid = rg.getCheckedRadioButtonId();
    rb = (RadioButton) findViewById(radiobuttonid);

    Toast.makeText(getBaseContext(), rb.getText(),Toast.LENGTH_LONG).show();
}
