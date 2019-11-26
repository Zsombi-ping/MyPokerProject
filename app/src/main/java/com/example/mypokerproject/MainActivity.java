package com.example.mypokerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int i = 0;
    //private ArrayList<Grup> grups = new ArrayList<>();
    private Button but;
    private EditText admin_name, grup_id;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = (Button) findViewById(R.id.buttonSend);
        admin_name = (EditText) findViewById(R.id.nameTxt);
        grup_id = (EditText) findViewById(R.id.grupTxt);
        but.setOnClickListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSend:
                final String aname = admin_name.getText().toString().trim();
                final String gid = grup_id.getText().toString().trim();
                if (aname == "" || gid == "") {
                    String msg = "Admin name or grup id is missing";
                    Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
                    toast.show();

                } else {

                    final Grup grup = new Grup(aname, gid);
                    final DatabaseReference grup_ref = mDatabase.child("grups");
                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(aname)) {
//                                Map<String, ArrayList<String>> m = (Map<String, ArrayList<String>>) dataSnapshot.getValue();
//                                ArrayList<String> val = m.get(aname);
//                                val.add(gid);
//                                m.put(aname, val);
//                                grup_ref.setValue(m);
                                grup_ref.child(aname).push().setValue(gid);
                                String msg = "Admin already exist " + dataSnapshot.getKey() + toString();
                                Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                                toast.show();
                            } else {
//                                Map<String, ArrayList<String>> m = new HashMap<>();
//                                ArrayList<String> val = new ArrayList<>();
//                                val.add(gid);
//                                m.put(aname, val);
                                grup_ref.child(aname).push().setValue(gid);

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    };
                    grup_ref.addListenerForSingleValueEvent(eventListener);

                }
                break;
        }
    }
}
