package com.carl.apt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.carl.annotation.Hello;
import com.carl.processgenerated.HelloWorld;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                    HelloWorld.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        testAnnotation();
    }


    @Hello(value = "test")
    private void testAnnotation() {

    }
}
