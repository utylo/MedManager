package com.example.utylo.medmanager.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.utylo.medmanager.R;
import com.example.utylo.medmanager.data.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    @BindView(R.id.users_name)
    TextView mUserName;
    @BindView(R.id.users_gender)
    TextView mUserGender;
    @BindView(R.id.users_age)
    TextView mUserAge;
    @BindView(R.id.users_location)
    TextView mUserLocation;
    @BindView(R.id.users_blood_group)
    TextView mUserBloodGroup;
    @BindView(R.id.users_weight)
    TextView mUsersWeight;
    @BindView(R.id.users_tempereture)
    TextView mUsersTempereture;
    @BindView(R.id.fab)
    FloatingActionButton fb;

    //Firebase
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // injects views
        ButterKnife.bind(this);

        // initialises storage
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //    getSupportActionBar().setTitle(User.getUserToken(this));

        this.setTitle(User.getUserToken(this));

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**Note: REMEMBER TO COMMENT THIS INTENT LINE IN OTHER TO HAVE AN ACCURATE ESPRESSO TEST RESULT OF THIS ACTIVITY/FILE**/
                startActivity(new Intent(ProfileActivity.this, PatientInfoActivity.class));
            }
        });

        // calls the method
        retrieveUserDetails();

    }

    /**
     * Note: when this method is called the first time during the execution of the activity
     * it will return a null value from the preference but when the activity is been
     * called from the PatientInfoActivity, it will then return a stored value because a value will be
     * stored in preferences before calling this activity.
     **/
    private void retrieveUserDetails() {
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);

        mUserName.setText((prefs.getString("name", null)));
        mUserGender.setText(prefs.getString("gender", null));
        mUserAge.setText(prefs.getString("age", null));
        mUserLocation.setText(prefs.getString("location", null));
        mUserBloodGroup.setText(prefs.getString("blood_group", null));
        mUsersWeight.setText(prefs.getString("weight", null));
        mUsersTempereture.setText(prefs.getString("temperature", null));
    }
}
