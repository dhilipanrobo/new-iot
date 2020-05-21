package com.school_bus_tracking_system.dkapp.iot_led;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
Button mon,moff,fan_off,fan_on;
TextView mtext;
FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        moff=findViewById(R.id.button7);
        mon = findViewById(R.id.button8);
        fan_off = findViewById(R.id.button1);
        fan_on = findViewById(R.id.button2);
        mtext = findViewById(R.id.textView);

        DatabaseReference myRef = database.getReference("ADC");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(Integer.class);
                mtext.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }



   public  void ON(View ON){
        fb("a","LED_STATUS");
        moff.getBackground().setAlpha(255);
        mon.getBackground().setAlpha(128);
      // Toast.makeText(this, "Led On", Toast.LENGTH_SHORT).show();
   }
    public  void OFF(View OFF){
        moff.getBackground().setAlpha(128);
        mon.getBackground().setAlpha(255);
        fb("b","LED_STATUS");
       // Toast.makeText(this, "Led OFF", Toast.LENGTH_SHORT).show();
    }


    public  void fan_ON(View ON){
        fb("a","FAN_STATUS");
        fan_off.getBackground().setAlpha(255);
        fan_on.getBackground().setAlpha(128);
       // Toast.makeText(this, "Led On", Toast.LENGTH_SHORT).show();
    }
    public  void fan_OFF(View OFF){
        fan_off.getBackground().setAlpha(128);
        fan_on.getBackground().setAlpha(255);
        fb("b","FAN_STATUS");
       // Toast.makeText(this, "Led OFF", Toast.LENGTH_SHORT).show();
    }

    void fb(String Value,String db){

        DatabaseReference myRef = database.getReference(db);

        myRef.setValue(Value);


    }
}
