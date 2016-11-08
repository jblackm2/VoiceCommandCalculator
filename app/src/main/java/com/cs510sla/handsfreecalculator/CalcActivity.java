package com.cs510sla.handsfreecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class CalcActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        Button zeroButton = (Button) findViewById(R.id.num0Button);
        zeroButton.setOnClickListener(this);

        Button oneButton = (Button) findViewById(R.id.num1Button);
        oneButton.setOnClickListener(this);

        Button twoButton = (Button) findViewById(R.id.num2Button);
        twoButton.setOnClickListener(this);

        Button threeButton = (Button) findViewById(R.id.num3Button);
        threeButton.setOnClickListener(this);

        Button fourButton = (Button) findViewById(R.id.num4Button);
        fourButton.setOnClickListener(this);

        Button fiveButton = (Button) findViewById(R.id.num5Button);
        fiveButton.setOnClickListener(this);

        Button sixButton = (Button) findViewById(R.id.num6Button);
        sixButton.setOnClickListener(this);

        Button sevenButton = (Button) findViewById(R.id.num7Button);
        sevenButton.setOnClickListener(this);

        Button eightButton = (Button) findViewById(R.id.num8Button);
        eightButton.setOnClickListener(this);

        Button nineButton = (Button) findViewById(R.id.num9Button);
        nineButton.setOnClickListener(this);

        Button plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(this);

        Button minusButton = (Button) findViewById(R.id.minusButton);
        minusButton.setOnClickListener(this);

        Button divButton = (Button) findViewById(R.id.divButton);
        divButton.setOnClickListener(this);

        Button timesButton = (Button) findViewById(R.id.timesButton);
        timesButton.setOnClickListener(this);

        Button equalsButton = (Button) findViewById(R.id.equalsButton);
        equalsButton.setOnClickListener(this);

        Button percButton = (Button) findViewById(R.id.percentButton);
        percButton.setOnClickListener(this);

        Button posNegButton = (Button) findViewById(R.id.posNegButton);
        posNegButton.setOnClickListener(this);

        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);

        Button voiceButton = (Button) findViewById(R.id.voiceButton);
        voiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
