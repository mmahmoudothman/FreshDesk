package com.example.freshdesk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatConfig;
import com.freshchat.consumer.sdk.FreshchatUser;
import com.freshchat.consumer.sdk.exception.MethodNotAllowedException;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FreshchatConfig config = new FreshchatConfig("d34e4444-47c4-4725-90f9-5f0181407c32", "23bc72dd-70f7-48e6-a8bc-0bee49ea0880");
        config.setDomain("msdk.freshchat.com");
        config.setCameraCaptureEnabled(true);
        config.setGallerySelectionEnabled(true);
        config.setResponseExpectationEnabled(true);
        Freshchat.getInstance(getApplicationContext()).init(config);

        FreshchatUser freshchatUser = Freshchat.getInstance(getApplicationContext()).getUser();
        freshchatUser.setFirstName("John");
        freshchatUser.setLastName("Doe");
        freshchatUser.setEmail("john.doe.1982@mail.com");
        freshchatUser.setPhone("+20", "1289456200");
        try {
            Freshchat.getInstance(getApplicationContext()).setUser(freshchatUser);
        } catch (MethodNotAllowedException e) {
            e.printStackTrace();
        }

        // Create a map and set required properties like below

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("Order Id", "01289456200");
        properties.put("Order Date", "24 Jan 2020");
        properties.put("Order Status ", "In-Transit");
// Call trackEvent by passing eventName and a map of properties
        Freshchat.trackEvent(this, "Event Nmae", properties);

    }
}