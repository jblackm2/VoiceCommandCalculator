package com.cs510sla.handsfreecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;


public class CalcActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView answerText;
    private TextToSpeech tts;
    private final int REQ_SPEECH_INPUT_CODE = 100;



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

        Button percButton = (Button) findViewById(R.id.modButton);
        percButton.setOnClickListener(this);

        Button posNegButton = (Button) findViewById(R.id.posNegButton);
        posNegButton.setOnClickListener(this);

        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);

        Button voiceButton = (Button) findViewById(R.id.voiceButton);
        voiceButton.setOnClickListener(this);

        answerText = (TextView) findViewById(R.id.answerText);
        answerText.setText("");

        tts = new TextToSpeech(CalcActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int code) {
                if (code == TextToSpeech.SUCCESS){
                    tts.setLanguage(Locale.US);
                }
                //Greeting message
                convertTTS("Welcome to a HandsFree Calculator. You can choose to either say your expression, or type it.");
            }
        });
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
                break;
            case R.id.modButton:
                //Todo:implement
                break;*/
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
                addButtonInput(button);
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        ArrayList<String> calculation = new ArrayList<>();
        if(reqCode == REQ_SPEECH_INPUT_CODE){
            if(resCode == RESULT_OK && data != null){
                calculation = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                setAnswerText(calculation.get(0));
                calculateAnswer();
            }
        }
    }

    public void setAnswerText(String text) {
        answerText.setText(text);
    }

    public String getAnswerText() {
        return (String) answerText.getText();
    }

    private void changePosNeg() {
    }

    private void calculateAnswer() {
        BigDecimal result = null;

        Expression expression = new Expression(answerText.getText().toString());
        result = expression.eval();
        setAnswerText(String.valueOf(result));
        convertTTS(String.valueOf(result));
    }

    private void clearText() {
        setAnswerText("");
    }

    private void addButtonInput(Button button) {
        String currentAnswerText = getAnswerText();
        setAnswerText(currentAnswerText + String.valueOf(button.getText()));
    }

    private void initVoice() {
        Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.prompt));

        startActivityForResult(speechIntent, REQ_SPEECH_INPUT_CODE);
    }

    public void convertTTS(String text){
        if (text.length() > 0){
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}
