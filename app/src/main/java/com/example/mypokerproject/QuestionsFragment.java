package com.example.mypokerproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ShareActionProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionsFragment extends Fragment implements View.OnClickListener {

    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<Boolean> isActivated = new ArrayList<Boolean>();
    private Button btn;
    private View myView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.questions_layout ,  container ,false );
        btn = (Button) myView.findViewById(R.id.fab);
        btn.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
     //   Utils.startFragment(getFragmentManager(),R.id.layoutHolder ,NewQuestionFragment());
    }

    private void getData()
    {
        final String sessionName = getActivity().getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE).getString("sessionName","");
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("sessions");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    if (ds.getKey() == sessionName)
                    {
                        for (DataSnapshot ch : ds.child("questions").getChildren())
                        {
                               names.add(ch.getKey().toString());
                               isActivated.add(ch.child("isActive").getValue(Boolean.class));
                        }

                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
