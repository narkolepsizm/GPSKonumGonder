package yasinaydin.gpskonumgonder;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static yasinaydin.gpskonumgonder.R.id.button;
import static yasinaydin.gpskonumgonder.R.id.editText2;
import static yasinaydin.gpskonumgonder.R.id.textView;

public class MainActivity extends AppCompatActivity {
    public String numara;
    LocationListener lls;
    public String url="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tw = (TextView) this.findViewById(textView);
        final EditText et = (EditText) this.findViewById(editText2);
        final Button gonder = (Button) this.findViewById(button);
        lls = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                url = ("http://maps.google.com/maps?daddr=" + String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()));
                gonder.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.SEND_SMS},10);
            }
            return;
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.SEND_SMS},333);
            }
            return;
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1001);
            }
            return;
        }
        final LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(!(lm.isProviderEnabled(LocationManager.GPS_PROVIDER))){
            Intent temiz=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(temiz);
        }
        lm.requestLocationUpdates("gps",5000,0,lls);
        gonder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                smsCak(String.valueOf(et.getText()), url);
            }
        });
    }

    public void smsCak(String num, String mes) {
        SmsManager sms = SmsManager.getDefault();
        numara = ("+9" + num);
        sms.sendTextMessage(numara,null,mes,null,null);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        if (requestCode == 333) {
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                System.exit(0);
            }
        }
        if (requestCode == 10) {
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                System.exit(0);
            }
        }
        if (requestCode == 1001) {
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                System.exit(0);
            }
        }
    }
}
