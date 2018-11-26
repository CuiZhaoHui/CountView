package com.dc.countview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RollTextView rollText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollText = findViewById(R.id.rollText);
        List<String> content = new ArrayList<>();
        content.add("草 / 赋得古原草送别");
        content.add("【作者】白居易 【朝代】唐");
        content.add("离离原上草");
        content.add("一岁一枯荣");
        content.add("野火烧不尽");
        content.add("春风吹又生");
        content.add("远芳侵古道");
        content.add("晴翠接荒城");
        content.add("又送王孙去");
        content.add("萋萋满别情");
        rollText.setContentList(content);
    }

    public void clickRoll(View v) {
        rollText.rollToNext();
    }

}
