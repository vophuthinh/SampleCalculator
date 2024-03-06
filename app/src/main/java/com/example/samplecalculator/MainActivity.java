package com.example.samplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView Screen;
    private String input="";
    private boolean clearResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Screen = findViewById(R.id.screen);
    }

    public void ButtonClick(View view) {
        Button button= (Button) view;
        String data = button.getText().toString();
        if (data.equals("=")) {
            if (input.isEmpty()) {
                showToast("Please enter a valid expression");
                return;
            }
        }
        switch (data){
            case "*":
                clearResult=false;
                evaluateExpression();
                input+="*";
                break;
            case "+":
                clearResult=false;
                evaluateExpression();
                input+="+";
                break;
            case "-":
                clearResult=false;
                evaluateExpression();
                input+="-";
                break;
            case "/":
                clearResult=false;
                evaluateExpression();
                input+="/";
                break;
            case "=":
                clearResult=true;
                evaluateExpression();
                break;
            default:
                if(clearResult){
                    input="";
                    clearResult=false;
                }
                input+=data;
        }

        Screen.setText(input);
    }
    public void evaluateExpression(){
        if(input.split("\\*").length==2){
            String numbers[]=input.split("\\*");
            try{
                double mul=Double.parseDouble(numbers[0])*Double.parseDouble(numbers[1]);
                input=mul+"";
            }
            catch (Exception e){
                showToast("An error occurred: " + e.getMessage());
            }
        }
        else if(input.split("/").length==2){
            String numbers[]=input.split("/");
            try{
                double divisor = Double.parseDouble(numbers[1]);
                if (divisor != 0) {
                    double div=Double.parseDouble(numbers[0])/divisor;
                    input=div+"";
                } else {
                    showToast("Cannot divide by zero");
                }
            }
            catch (Exception e){
                showToast("An error occurred: " + e.getMessage());
            }
        }
        else if(input.split("\\+").length==2){
            String numbers[]=input.split("\\+");
            try{
                double sum=Double.parseDouble(numbers[0])+Double.parseDouble(numbers[1]);
                input=sum+"";
            }
            catch (Exception e){
                showToast("An error occurred: " + e.getMessage());
            }
        }
        else if(input.split("\\-").length>1){
            String numbers[]=input.split("\\-");
            if(numbers[0]=="" && numbers.length==2){
                numbers[0]=0+"";
            }
            try{
                double sub=0;
                if(numbers.length==2) {
                    sub = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                }
                else if(numbers.length==3){
                    sub = -Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                }
                input=sub+"";
            }
            catch (Exception e){
                showToast("An error occurred: " + e.getMessage());
            }
        }
        String n[]=input.split("\\.");
        if(n.length>1){
            if(n[1].equals("0")){
                input=n[0];
            }
        }
        Screen.setText(input);
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
