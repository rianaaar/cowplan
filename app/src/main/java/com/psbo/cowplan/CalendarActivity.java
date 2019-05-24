package com.psbo.cowplan;

import android.app.usage.UsageEvents;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private Button button;
    CalendarView calendarView;
    TextView myDate, theDate;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.mydate);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //String date = dayOfMonth + "/" + (month+1) + "/" + year;
                String date = year + "/" + (month+1) + "/" + dayOfMonth;
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                // display the current date
                String CurrentDate = mYear + "/" + mMonth + "/" + mDay;

                String dateInString = date; // Start date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                c = Calendar.getInstance();

                try {
                    c.setTime(sdf.parse(dateInString));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                c.add(Calendar.DATE, 21);//insert the number of days you want to be added to the current date
                sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date resultdate = new Date(c.getTimeInMillis());
                dateInString = sdf.format(resultdate);
                myDate.setText(dateInString);
            }
        });
        button = (Button) findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_input_date();
            }
        });

    }
    public void open_input_date() {
        Intent intent = new Intent(this, PilihKategori.class);
        startActivity(intent);
    }


};