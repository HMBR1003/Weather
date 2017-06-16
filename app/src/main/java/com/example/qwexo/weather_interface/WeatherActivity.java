package com.example.qwexo.weather_interface;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity {

    WeatherListViewAdapter adapter;
    DatabaseReference fireDB;
    Weather we;
    Dust dust;
    ValueEventListener listener;
    ProgressDialog progress;

    TextView dateText, amTempText, amSkyText, pmTempText, pmSkyText, dustGradeText, dustValueText;
    ImageView pmSkyImage, amSkyImage;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ListView listView = (ListView)findViewById(R.id.listview1);
        dateText = (TextView)findViewById(R.id.dateText);
        amTempText = (TextView)findViewById(R.id.amTempText);
        amSkyImage = (ImageView)findViewById(R.id.amSkyImage);
        amSkyText = (TextView)findViewById(R.id.amSkyText);
        pmTempText = (TextView)findViewById(R.id.pmTempText);
        pmSkyImage = (ImageView)findViewById(R.id.pmSkyImage);
        pmSkyText = (TextView)findViewById(R.id.pmSkyText);
        dustGradeText = (TextView)findViewById(R.id.dustGradeText);
        dustValueText = (TextView)findViewById(R.id.dustValueText);

        we = new Weather();
        adapter = new WeatherListViewAdapter();
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                int count = 1;
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    we = data.getValue(Weather.class);
                    dust = data.getValue(Dust.class);
                    if(count == 1){
                        dateText.setText(we.date + "\n(오늘)");
                        amTempText.setText(we.am_temp);
                        amSkyImage.setImageDrawable(getWeatherImage(we.am_sky_code));
                        amSkyText.setText(we.am_sky_name);
                        pmTempText.setText(we.pm_temp);
                        pmSkyImage.setImageDrawable(getWeatherImage(we.pm_sky_code));
                        pmSkyText.setText(we.pm_sky_name);
                    }
                    if(dust.grade != null){
                        dustGradeText.setText(dust.grade);
                        dustValueText.setText(dust.value);
                    }
                    if(we.date != null && count != 1) {
                        adapter.addItem(we.date, we.am_temp, getWeatherImage(we.am_sky_code), we.am_sky_name, we.pm_temp,
                                getWeatherImage(we.pm_sky_code), we.pm_sky_name);
                    }
                    count++;
                }
                adapter.notifyDataSetChanged();
                progress.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        listView.setAdapter(adapter);
    }

    private Drawable getWeatherImage(String code) {
        switch(code){
            case "SKY_S00":
                return getResources().getDrawable(R.drawable._s00);
            case "SKY_S01":
                return getResources().getDrawable(R.drawable._s01);
            case "SKY_S02":
                return getResources().getDrawable(R.drawable._s02);
            case "SKY_S03":
                return getResources().getDrawable(R.drawable._s03);
            case "SKY_S04":
                return getResources().getDrawable(R.drawable._s04);
            case "SKY_S05":
                return getResources().getDrawable(R.drawable._s05);
            case "SKY_S06":
                return getResources().getDrawable(R.drawable._s06);
            case "SKY_S07":
                return getResources().getDrawable(R.drawable._s07);
            case "SKY_S08":
                return getResources().getDrawable(R.drawable._s08);
            case "SKY_S09":
                return getResources().getDrawable(R.drawable._s09);
            case "SKY_S10":
                return getResources().getDrawable(R.drawable._s10);
            case "SKY_S11":
                return getResources().getDrawable(R.drawable._s11);
            case "SKY_S12":
                return getResources().getDrawable(R.drawable._s12);
            case "SKY_S13":
                return getResources().getDrawable(R.drawable._s13);
            case "SKY_S14":
                return getResources().getDrawable(R.drawable._s14);
            case "SKY_W00":
                return getResources().getDrawable(R.drawable._w00);
            case "SKY_W01":
                return getResources().getDrawable(R.drawable._w01);
            case "SKY_W02":
                return getResources().getDrawable(R.drawable._w02);
            case "SKY_W03":
                return getResources().getDrawable(R.drawable._w03);
            case "SKY_W04":
                return getResources().getDrawable(R.drawable._w04);
            case "SKY_W07":
                return getResources().getDrawable(R.drawable._w07);
            case "SKY_W09":
                return getResources().getDrawable(R.drawable._w09);
            case "SKY_W10":
                return getResources().getDrawable(R.drawable._w10);
            case "SKY_W11":
                return getResources().getDrawable(R.drawable._w11);
            case "SKY_W12":
                return getResources().getDrawable(R.drawable._w12);
            case "SKY_W13":
                return getResources().getDrawable(R.drawable._w13);
            default:
                return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progress = new ProgressDialog(WeatherActivity.this);
        progress.setProgress(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("데이터를 불러오는중입니다.");
        progress.setCancelable(false);
        progress.show();

        intent = getIntent();
        String city = intent.getStringExtra("city");
//        String city = "서울송파구잠실동";
        fireDB = FirebaseDatabase.getInstance().getReference();
        fireDB.child("Weather").child(city).addValueEventListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fireDB.removeEventListener(listener);
    }
}
