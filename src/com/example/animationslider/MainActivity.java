package com.example.animationslider;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	CoverFlow coverFlow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coverFlow = (CoverFlow)findViewById(R.id.coverFlow1);
        
        ImageAdapter coverImageAdapter = new ImageAdapter(getApplicationContext());
		coverImageAdapter.createReflectedImages();
        coverFlow.setAdapter(coverImageAdapter);
        coverFlow.setSpacing(-25);
		coverFlow.setSelection(1, true);
		coverFlow.setAnimationDuration(1000);
    }
    
}
