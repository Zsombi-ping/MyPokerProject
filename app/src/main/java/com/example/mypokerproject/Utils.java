package com.example.mypokerproject;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Utils {

     public static final String MY_PREFS_NAME = "admin app";

     public static void makeToast(Context context , String msg)
     {
         Toast.makeText(context , msg , Toast.LENGTH_LONG).show();
     }

     public static void startFragment (FragmentManager fragmentManager , int layoutId, Fragment fragment)
     {
          fragmentManager.beginTransaction().replace(layoutId,fragment).addToBackStack(null).commit();

     }

}
