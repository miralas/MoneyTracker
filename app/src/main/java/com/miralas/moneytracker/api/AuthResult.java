package com.miralas.moneytracker.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tiburon on 02/04/2018.
 */

public class AuthResult {
    public String status;
    public int id;
    @SerializedName("auth_token")
    public String token;
}
