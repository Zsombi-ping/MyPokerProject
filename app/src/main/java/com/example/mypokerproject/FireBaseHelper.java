package com.example.mypokerproject;

import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FireBaseHelper {

    private static final String TAG = "FireBaseHelper";
    private HashMap<String, ArrayList<String>> sessions = new HashMap<String, ArrayList<String>>();
    private DatabaseReference myref = FirebaseDatabase.getInstance().getReference("sessions");

    public FireBaseHelper(final HashMap<String, ArrayList<String>> sessions, DatabaseReference myref) {

        this.myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                    final ArrayList<String> questionList = new ArrayList<String>();
                    DatabaseReference q_ref = userSnapshot.child("questions").getRef();
                    q_ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {

                            for (DataSnapshot ds : snapshot.getChildren()) {

                                questionList.add(ds.getKey().toString());
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    sessions.put(userSnapshot.getKey().toString(), questionList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value", databaseError.toException());
            }

        });

    }

    public Boolean checkSession(String sessionName) {
        return sessions.containsKey(sessionName);
    }

    public void createSession(String sessionName) {
        myref.child(sessionName);
    }

    public void createQuestion(String sessionName, String questionName) {
        myref.child(sessionName).child("questions").child(questionName).child("isActive").setValue(false);
    }

    public void activateQuestion(String sessionName, final String questionName) {
        myref.child(sessionName).child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (questionName == ds.getKey()) {
                        ds.getRef().child("isActive").setValue(true);
                    } else {
                        ds.getRef().child("isActive").setValue(false);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value", databaseError.toException());

            }
        });
    }

    public void deactivateQuestion(String sessionName, final String questionName) {
        myref.child(sessionName).child("questions").child(questionName).child("isActive").setValue(false);

    }


}

