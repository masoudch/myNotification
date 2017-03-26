package androidnavigationdrawermasterdetail.inducesmile.com.mynotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder myNotify;
    private static  final int myUniqueId=2658;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myNotify=new NotificationCompat.Builder(this);
        myNotify.setAutoCancel(true); //when ever click on notification deletes it from top status bar


    }

    public void clickfornotifyIcon(View view) {
        myNotify.setSmallIcon(R.drawable.myicon);
        myNotify.setTicker("This is the Ticker");
        myNotify.setWhen(System.currentTimeMillis());
        myNotify.setContentTitle("Here is title");
        myNotify.setContentText("here is content text");

        Intent i=new Intent(this,MainActivity.class); //this is the part of the app that when user clicks on this activity will be shown
        PendingIntent pi=PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        myNotify.setContentIntent(pi);
        NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(myUniqueId,myNotify.build());
    }

    public void btnSaveme(View view) {
        SharedPreferences sp=getSharedPreferences("mydatafile", Context.MODE_PRIVATE); //private means no other app can access it
        SharedPreferences.Editor spe=sp.edit();
        EditText txt1=(EditText) findViewById(R.id.editText1);
        spe.putString("username",txt1.getText().toString());
        spe.apply();
        Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
    }

    public void btnLoadme(View view) {
        SharedPreferences sp=getSharedPreferences("mydatafile", Context.MODE_PRIVATE); //private means no other app can access it

        TextView lbl1=(TextView)findViewById(R.id.textView2);
        lbl1.setText(sp.getString("username",""));
        Toast.makeText(this,"loaded",Toast.LENGTH_LONG).show();
    }
}
