package com.psbo.cowplan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    private Button button;
    private CalendarView calendarView;
    TextView myDate;

    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //calendarView = (CalendarView) findViewById(R.id.compactcalendar_view);

        //set event
        Event ev1 = new Event(Color.RED, 1477054800000L,"Test");
        compactCalendar.addEvent(ev1);

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                if(dateClicked.toString().compareTo("Fri Oct 21 09:00:00 AST 2019")==0){
                    Toast.makeText(context, "Prediksi Birahi Sapi", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Tidak ada prediksi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
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
    //String dt = "2008-01-01";  // Start date
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //Calendar c = Calendar.getInstance();
    //c.setTime(sdf.parse(dt));
    //c.add(Calendar.DATE, 1);  // number of days to add
    //dt = sdf.format(c.getTime());  // dt is now the new date

}

