package com.psbo.cowplan;

import android.app.usage.UsageEvents;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button button;
    CalendarView calendarView;
    TextView myDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.mydate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                myDate.setText(date);
                int date2 = convertToJulian(date);
                int prediksibirahi = siklusbirahi(date2);
                String datebirahi = JulianToDate(prediksibirahi);

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

    public int convertToJulian(String unformattedDate)
    {
        /*Unformatted Date: ddmmyyyy*/
        int resultJulian = 0;
        if(unformattedDate.length() > 0)
        {
            /*Days of month*/
            int[] monthValues = {31,28,31,30,31,30,31,31,30,31,30,31};

            String dayS, monthS, yearS;
            dayS = unformattedDate.substring(0,2);
            monthS = unformattedDate.substring(3, 5);
            yearS = unformattedDate.substring(6, 10);

            /*Convert to Integer*/
            int day = Integer.valueOf(dayS);
            int month = Integer.valueOf(monthS);
            int year = Integer.valueOf(yearS);

            //Leap year check
            if(year % 4 == 0)
            {
                monthValues[1] = 29;
            }
            //Start building Julian date
            String julianDate = "1";
            //last two digit of year: 2012 ==> 12
            julianDate += yearS.substring(2,4);

            int julianDays = 0;
            for (int i=0; i < month-1; i++)
            {
                julianDays += monthValues[i];
            }
            julianDays += day;

            if(String.valueOf(julianDays).length() < 2)
            {
                julianDate += "00";
            }
            if(String.valueOf(julianDays).length() < 3)
            {
                julianDate += "0";
            }

            julianDate += String.valueOf(julianDays);
            resultJulian =  Integer.valueOf(julianDate);
        }
        return resultJulian;
    }
    public int siklusbirahi(int date2){
        int hasil= date2+21;
        return hasil;
    }
    public String JulianToDate(int tgl){
        String tanggal="0";
        return tanggal; //ini blm selesai
    }

};
