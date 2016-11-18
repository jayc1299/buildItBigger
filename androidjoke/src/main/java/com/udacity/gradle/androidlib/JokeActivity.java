package com.udacity.gradle.androidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String TAG_JOKE = "tag_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Get joke passed in via intent. Otherwise use default
        String joke = getString(R.string.app_name);
        if (getIntent() != null && getIntent().getExtras() != null) {
            joke = getIntent().getExtras().getString(TAG_JOKE);
        }

        //Set main text
        TextView jokeTxtView = (TextView) findViewById(R.id.activity_joke_joke);
        if(jokeTxtView != null){
            jokeTxtView.setText(joke);
        }

        findViewById(R.id.activity_joke_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}