package pl.galusgaming.lab3;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class ThirdActivity extends AppCompatActivity {
    private Button btnStart, btnStop;
    private TextView tvWidth, tvHeight;
    public LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        tvWidth = findViewById(R.id.tvWidth);
        tvHeight = findViewById(R.id.tvHeight);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ActivityCompat.checkSelfPermission(ThirdActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == getPackageManager().PERMISSION_GRANTED) {
                    if(isGpsEnabled()){
                        //getLocation();
                    }else {
                        TurnOnGPS();
                    }
                }


            }
        }
        });


    }

    private void TurnOnGPS() {
    }

    private Boolean isGpsEnabled(){
        locationManager =null;
        boolean gpsEnabled = false;
        if(locationManager == null){
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        }
        gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return gpsEnabled;
    }
}