package com.codeup.kidsync.twillio;

public class CheckCode {

    public boolean goodCode(String code) {
        int result = Integer.parseInt(code);
        
        if (result >= 2000 && result <= 5000) {
            return true;
        } else {
            return false;
        }
    }

}
