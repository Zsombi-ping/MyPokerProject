package com.example.mypokerproject;

public class Grup {

   private String admin_name;
   private String grup_id;

    public Grup(String admin_name, String grup_id) {
        this.admin_name = admin_name;
        this.grup_id = grup_id;
    }


    public String getGrup_id() {
        return grup_id;
    }


    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_id(String admin_name) {
        this.admin_name = admin_name;
    }

    public void setGrup_id(String grup_id) {
        this.grup_id = grup_id;
    }


}
