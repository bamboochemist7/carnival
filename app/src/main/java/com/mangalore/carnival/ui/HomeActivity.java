package com.mangalore.carnival.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.mangalore.carnival.R;
import com.mangalore.carnival.ui.navigation.*;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpToolbar();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            findViewById(R.id.product_grid)
                    .setBackgroundResource(R.drawable.shr_product_grid_background_shape);
        }
    }

    private void setUpToolbar( ) {
        Toolbar toolbar = findViewById(R.id.app_bar);
        AppCompatActivity activity = this;
        if (activity != null)  {
            activity.setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                this,
                findViewById(R.id.product_grid),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.shr_branded_menu), // Menu open icon
                getResources().getDrawable(R.drawable.shr_close_menu))); // Menu close icon
    }
}
