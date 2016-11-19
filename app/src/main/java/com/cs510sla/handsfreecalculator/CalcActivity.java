package com.cs510sla.handsfreecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalcActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView answerText;
    private String screenFormula;

    public void setAnswerText(String text) {
        String currentAnswerText = getAnswerText();
        answerText.setText(currentAnswerText + text);
    }

    public String getAnswerText() {
        return (String) answerText.getText();
    }

    private void changePosNeg() {
    }

    private void initVoice() {
    }

    private void calculateAnswer() {
    }

    private void clearText() {
    }

    public CalcActivity(){
        answerText = null;
        screenFormula = "";
    }

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

        TextView answerText = (TextView) findViewById(R.id.answerText);

        answerText.setText(screenFormula);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) findViewById(view.getId());
        switch (view.getId()){

            case R.id.equalsButton:
                calculateAnswer();
                break;
            /*case R.id.plusButton:
                //Todo:implement
                break;
            case R.id.minusButton:
                //Todo:implement
                break;
            case R.id.timesButton:
                //Todo:implement
                break;
            case R.id.divButton:
                //Todo:implement
                break;*/
            case R.id.percentButton:
                //Todo:implement
                break;
            case R.id.posNegButton:
                changePosNeg();
                break;
            case R.id.clearButton:
                clearText();
                break;
            case R.id.voiceButton:
                initVoice();
                break;
            //should catch all buttons where the text is what we want input
            default:
                setAnswerText(String.valueOf(button.getText()));
        }
    }

}
