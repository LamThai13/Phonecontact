package com.example.lamthai.phonecontact.model;

/**
 * Created by Thai on 12/29/2016.
 */

public class Contact {
    private boolean isMale;
    private String mname;
    private String mNumber;

    public Contact(boolean isMale, String mname, String mNumber) {
        this.isMale = isMale;
        this.mname = mname;
        this.mNumber = mNumber;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }
}
