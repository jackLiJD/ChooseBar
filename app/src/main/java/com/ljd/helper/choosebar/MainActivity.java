package com.ljd.helper.choosebar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ChooseBtn choosebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choosebar=findViewById(R.id.choosebar);
        choosebar.setDefaultSwitch(false);
        choosebar.setChangeListener(new ChooseBtn.ChangeListener() {
            @Override
            public void changeListener(boolean state) {
                if (state) {
                    Toast.makeText(MainActivity.this, "开启", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
