package com.example.mypokerproject;

import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class LoginFrag extends Fragment {

    private View myView;
    private Button button;
    private EditText txt;
    private FireBaseHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.login_layout, container, false);
        button = (Button) myView.findViewById(R.id.loginBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt = (EditText) myView.findViewById(R.id.sessionTxt);
                String sessionName = txt.toString().trim();
                if (!TextUtils.isEmpty(sessionName)) {

                    if (!dbHelper.checkSession(sessionName)) {
                        dbHelper.createSession(sessionName);
                    }
                    getActivity().getSharedPreferences(Utils.MY_PREFS_NAME, MODE_PRIVATE).edit().putString("sessionName", sessionName).apply();
                    //Utils.startFragment(getFragmentManager(),R.id.layoutHolder,QuestionsFragment);
                } else {
                    Utils.makeToast(myView.getContext(), "Session field is empty");

                }
            }
        });
        return myView;
    }
}


