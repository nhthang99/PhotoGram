package com.nht.instagram.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nht.instagram.R;
import com.nht.instagram.Utils.BottomNavigationViewHelper;
import com.nht.instagram.Utils.GridImageAdapter;
import com.nht.instagram.Utils.UniversalImageLoader;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private static final int NUM_GRID_COLUMNS = 3;
    private static final byte ACTIVITY_NUM = 4;

    Context mContext = ProfileActivity.this;

    private ProgressBar mProgressBar;
    private ImageView mProfilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: starting.");

        setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfilePhoto();

        tempGridSetup();
    }

    private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("http://thehappypuppysite.com/wp-content/uploads/2017/09/cute4.jpg");
        imgURLs.add("https://d17fnq9dkz9hgj.cloudfront.net/uploads/2018/03/Maine-Coon_02.jpg");
        imgURLs.add("https://d17fnq9dkz9hgj.cloudfront.net/uploads/2018/03/British-Shorthair_01.jpg");
        imgURLs.add("https://d17fnq9dkz9hgj.cloudfront.net/uploads/2018/03/Bengal-Cat_02.jpg");
        imgURLs.add("http://getwallpapers.com/wallpaper/full/f/9/e/939484-wallpapers-for-laptop-background-1920x1200-hd-1080p.jpg");

        setupImageGrid(imgURLs);
    }

    private void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView = findViewById(R.id.gridView);

        int gridWidth = gridView.getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth / NUM_GRID_COLUMNS;

        GridImageAdapter gridImageAdapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        gridView.setAdapter(gridImageAdapter);
    }

    private void setProfilePhoto(){
        Log.d(TAG, "setProfilePhoto: setting profile photo");
        String imgURL = "thehappypuppysite.com/wp-content/uploads/2017/09/cute4.jpg";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, mProgressBar, "http://");
    }

    private void setupActivityWidgets(){
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
        mProfilePhoto = findViewById(R.id.profile_photo);
    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to account setting");
                Intent intent = new Intent(mContext, AccountSettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableBottomNavigationView(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_account_full));
        menuItem.setChecked(true);
    }
}
