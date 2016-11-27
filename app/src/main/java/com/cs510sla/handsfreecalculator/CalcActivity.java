package com.cs510sla.handsfreecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Locale;


public class CalcActivity extends AppCompatActivity implements View.OnClickListener{

    public TextView answerText;
    private TextToSpeech tts;
    private final int REQ_SPEECH_INPUT_CODE = 100;
    private final String TIMES = "*";
    private final String MINUS = "-";
    private final String PLUS = "+";
    private final String DIVIDE = "/";
    private final String MOD = "%";
    private final String NEG = "-";
    private final String DEC = ".";
    private final String POW = "^";



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

        Button decimalButton = (Button) findViewById(R.id.decimalButton);
        decimalButton.setOnClickListener(this);

        Button leftParenButton = (Button) findViewById(R.id.leftParenButton);
        leftParenButton.setOnClickListener(this);

        Button rightParenButton = (Button) findViewById(R.id.rightParenButton);
        rightParenButton.setOnClickListener(this);

        Button powerButton = (Button) findViewById(R.id.powerButton);
        powerButton.setOnClickListener(this);

        Button sqrtButton = (Button) findViewById(R.id.piButton);
        sqrtButton.setOnClickListener(this);

        ImageButton voiceButton = (ImageButton) findViewById(R.id.voiceButton);
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
        ImageButton imgButton = null;
        Button button = null;
        if (view.getId() == R.id.voiceButton) {
            imgButton = (ImageButton) findViewById(view.getId());
        }
        else {
            button = (Button) findViewById(view.getId());
        }

        switch (view.getId()){

            case R.id.equalsButton:
                calculateAnswer();
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
                addButtonInput(button);
        }
    }

    public void setAnswerText(String text) {
        answerText.setText(text);
    }

    public String getAnswerText() {
        return (String) answerText.getText();
    }

    private void changePosNeg() {
        String currentAnswerText = getAnswerText();
        setAnswerText(currentAnswerText + "-");
    }

    private void calculateAnswer() {
        BigDecimal result = null;

        Expression expression = new Expression(answerText.getText().toString());
        try {
            result = expression.eval();
        }
        catch (Exception e) {
            showError("Illegal calculation.");
            return;
        }
        showResults(result);
    }

    private void showError(String error) {
        setAnswerText(error);
        convertTTS(error);
    }

    private void showResults(BigDecimal result) {
        setAnswerText(String.valueOf(result));
        convertTTS("The answer is " + String.valueOf(result));
    }

    protected void clearText() {
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

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        ArrayList<String> calculation = new ArrayList<>();
        if(reqCode == REQ_SPEECH_INPUT_CODE){
            if(resCode == RESULT_OK && data != null){
                calculation = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (calculation.get(0).toLowerCase().contains("clear")) {
                    clearText();
                }
                else{
                    String filteredCalculation = filterInput(calculation.get(0).toLowerCase());
                    setAnswerText(getAnswerText() + filteredCalculation);
                    calculateAnswer();
                }
            }
        }
    }

    //Function to filter out non-operand terms like 'plus', 'times'
    protected String filterInput(String input) {

        String[] splitInput;
        splitInput = input.split(" ");
        String result = "";
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("x",TIMES);
        newValueMap.put("times", TIMES);
        newValueMap.put("multiplied", TIMES);
        newValueMap.put("multiply", TIMES);
        newValueMap.put("plus", PLUS);
        newValueMap.put("minus", MINUS);
        newValueMap.put("subtract", MINUS);
        newValueMap.put("÷", DIVIDE);
        newValueMap.put("divide", DIVIDE);
        newValueMap.put("divided", DIVIDE);
        newValueMap.put("mod", MOD);
        newValueMap.put("modulo", MOD);
        newValueMap.put("neg", NEG);
        newValueMap.put("negative", NEG);
        newValueMap.put("point", DEC);
        newValueMap.put("power", POW);
        newValueMap.put("raised", POW);
        newValueMap.put("to", "");
        newValueMap.put("of", "");
        newValueMap.put("the", "");
        newValueMap.put("by", "");
        newValueMap.put("bye", "");
        newValueMap.put("parenthesis", "");
        newValueMap.put("parentheses", "");
        newValueMap.put("paren", "");
        newValueMap.put("left", "");
        newValueMap.put("right", "");
        newValueMap.put("open", "");
        newValueMap.put("close", "");

        for (int i = 0; i < splitInput.length; i++){
            if (newValueMap.containsKey(splitInput[i])){//if the string contains a matching term
                if (splitInput[i].contains("paren") && i > 0) {
                    if (splitInput[i-1].equalsIgnoreCase("left") || splitInput[i-1].equalsIgnoreCase("open")) {
                        result += "(";
                    }
                    else if (splitInput[i-1].equalsIgnoreCase("right") || splitInput[i-1].equalsIgnoreCase("close")) {
                        result += ")";
                    }
                }
                else {
                    result += newValueMap.get(splitInput[i]);//replace it with the operand in the hashtable
                    //splitInput[i] = newValueMap.get(splitInput[i]);
                }
            }
            else if (splitInput[i].startsWith(".") || splitInput[i].startsWith("point")){//If someone says .5, this will append a 0
                result += ("0" + splitInput[i]);
            }
            else{
                result += splitInput[i];
            }
        }
        return result;
    }

    public void convertTTS(String text){
        if (text.length() > 0){
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}
