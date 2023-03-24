package yasinaydin.gpskonumgonder;

import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final LocationManager lm2=(LocationManager)getSystemService(LOCATION_SERVICE);
        final Intent gpsac=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        if(!(lm2.isProviderEnabled(LocationManager.GPS_PROVIDER))){
        setResult(RESULT_OK,gpsac);
        startActivityForResult(gpsac,1);
        }
        else{
            finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (!(resultCode == RESULT_OK)) {
                System.exit(0);
            }
        }
    }
}
