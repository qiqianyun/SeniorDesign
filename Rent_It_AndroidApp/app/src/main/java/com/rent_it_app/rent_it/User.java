package com.rent_it_app.rent_it;

/**
 * Created by Mizz on 1/13/17.
 */

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String firstname;
    public String lastname;
    public String displayname;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String firstname, String lastname, String displayname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.displayname = displayname;

    }
}