package pl.galusgaming.lab3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ThirdActivity extends AppCompatActivity implements LocationListener {
    private Button btnStart, btnStop;
    private TextView tvWidth, tvHeight;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.BtnStop);
        tvWidth = findViewById(R.id.tvWidth);
        tvHeight = findViewById(R.id.tvHeight);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationUpdates();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLocationUpdates();
            }
        });
    }

    private void startLocationUpdates() {
        tvWidth.setText("started");
        tvHeight.setText("started");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    private void stopLocationUpdates() {
        locationManager.removeUpdates(this);
        tvWidth.setText("stopped");
        tvHeight.setText("stopped");
    }

    @Override
    public void onLocationChanged(Location location) {
        tvWidth.setText(String.valueOf(location.getLatitude()));
        tvHeight.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            }
        }
    }
}
