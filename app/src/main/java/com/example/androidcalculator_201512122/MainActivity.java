package com.example.androidcalculator_201512122;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidcalculator_201512122.databinding.ActivityMainBinding;

import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    // 입력한 숫자가 첫 번째로 입력한 것인지 아닌지 확인해야 한다.
    // 처음 실행해서 숫자를 넣으면 첫 번째 숫자이기 때문이다.
    boolean isFirstInput = true;
    boolean isOperatorClick = false;
    double inputNumber = 0;
    double resultNumber = 0;
    // 초기 값은 ＝로 초기화 ㄷ+'한자' 키 조합을 만듬
    String operator = "＝";
    String lastOperator = "＋";

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.activityMainBinding.getRoot());
    }
    public void numberBtnClick(View view){
        // 버튼이라는 변수를 선언하고 버튼안에 ID를 저장하면서 메모리 공간을 차지하게 되지만
        // Tag를 이용하면 버튼이라는 변수를 선언하지 않아도 된다.
        // Button button = findViewById(view.getId());
        // String getButtonText = button.getText().toString();
        String getTag = view.getTag().toString();
        if(isFirstInput){
            activityMainBinding.resultTextView.setText(getTag);
            this.isFirstInput = false;
            if(operator.equals("＝")){
                activityMainBinding.mathTextView.setText(null);
            }
        }else{
            if(activityMainBinding.resultTextView.getText().toString().equals("0")){
                Toast.makeText(this, "this is zero", Toast.LENGTH_SHORT).show();
                this.isFirstInput = true;
            }else{
                activityMainBinding.resultTextView.append(getTag);
            }
        }
    }

    public void allClearBtnClick(View view){
        activityMainBinding.resultTextView.setText("0");
        activityMainBinding.mathTextView.setText("");
        this.inputNumber = 0;
        this.resultNumber = 0;
        this.operator = "＋";
        this.isFirstInput = true;
        this.isOperatorClick = false;
    }

    //구현되지 않음
    public void clearBtnClick(View view){
        if(!isFirstInput){
            activityMainBinding.resultTextView.setText("0");
            isFirstInput = true;
        }else{
            activityMainBinding.resultTextView.setText("0");
            activityMainBinding.mathTextView.setText("");
            this.inputNumber = 0;
            this.resultNumber = 0;
            this.operator = "＋";
            this.isFirstInput = true;
            this.isOperatorClick = false;
        }
    }

    //계산값은 수정하면 안된다.
    public void deleteBtnClick(View view){
        if(!isFirstInput){
            String getResultText = activityMainBinding.resultTextView.getText().toString();
            if(getResultText.length() > 1) {
                String subString = getResultText.substring(0, getResultText.length() - 1);
                activityMainBinding.resultTextView.setText(subString);
            }else{
                if(activityMainBinding.resultTextView.getText().toString().equals("0")){
                    activityMainBinding.resultTextView.setText("0");
                    activityMainBinding.mathTextView.setText("");
                    this.inputNumber = 0;
                    this.resultNumber = 0;
                    this.operator = "＋";
                    this.isFirstInput = true;
                    this.isOperatorClick = false;
                }else{
                    activityMainBinding.resultTextView.setText("0");
                    isFirstInput = true;
                }
            }
        }else{
            activityMainBinding.resultTextView.setText("0");
            activityMainBinding.mathTextView.setText("");
            this.inputNumber = 0;
            this.resultNumber = 0;
            this.operator = "＋";
            this.isFirstInput = true;
            this.isOperatorClick = false;
        }
    }
    //구현중
    public void pointBtn(View view){
        if(isFirstInput){
            activityMainBinding.resultTextView.setText("0"+view.getTag().toString());
            this.isFirstInput = false;
        }else{
            if(activityMainBinding.resultTextView.getText().toString().contains(".")){
                Toast.makeText(this, "it has dot", Toast.LENGTH_SHORT).show();
            }
            activityMainBinding.resultTextView.append(view.getTag().toString());
        }
    }

    //"＝"
    public void equalsBtnClick(View view){
        if(isFirstInput){
            if(isOperatorClick){
                activityMainBinding.mathTextView.setText(resultNumber + " " + lastOperator + " "  + inputNumber + " =");
                this.resultNumber = calculator(resultNumber, inputNumber, lastOperator);
                activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
            }
        }else{
            this.inputNumber = Double.parseDouble(activityMainBinding.resultTextView.getText().toString());
            this.resultNumber = calculator(resultNumber, inputNumber, operator);
            activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
            this.isFirstInput = true;
            this.operator = view.getTag().toString();
            activityMainBinding.mathTextView.append(inputNumber + " " + operator + " ");
        }
    }

    public void operatorClick(View view){
        this.isOperatorClick = true;
        this.lastOperator = view.getTag().toString();
        if(isFirstInput){
            if(operator.equals("＝")){
                this.operator = view.getTag().toString();
                resultNumber = Double.parseDouble(activityMainBinding.resultTextView.getText().toString());
                activityMainBinding.mathTextView.setText(String.valueOf(resultNumber + " "  + operator + " "));
            }else{
                this.operator = view.getTag().toString();
                Log.e("operator clicked", operator);
                // operator가 이미 클릭되었거나 아무것도 없는 상태에서 클릭이 되는 상태
                String getMathText = activityMainBinding.mathTextView.getText().toString();
                if(getMathText.length()>2){
                    String subString = getMathText.substring(0, getMathText.length() - 2);
                    activityMainBinding.mathTextView.setText(subString + " " + operator);
                }else{
                    activityMainBinding.mathTextView.setText("0" + " " + operator);
                }
            }

        }else{
                this.inputNumber = Double.parseDouble(activityMainBinding.resultTextView.getText().toString());
                this.resultNumber = calculator(resultNumber, inputNumber, operator);
                activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
                this.isFirstInput = true;
                this.operator = view.getTag().toString();
                activityMainBinding.mathTextView.setText(inputNumber + " " + operator + " ");
        }
    }

    public void percentBtnClick(View view){
        this.resultNumber = calculator(resultNumber, inputNumber, operator);
        activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
        activityMainBinding.mathTextView.setText(String.valueOf(resultNumber));
    }

    public void reverseNumBtnClick(View view){
        this.resultNumber = calculator(resultNumber, inputNumber, operator);
        activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
        activityMainBinding.mathTextView.setText(String.valueOf(resultNumber));
    }

    public void squareNumBtnClick(View view){
        this.resultNumber = calculator(resultNumber, inputNumber, operator);
        activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
        activityMainBinding.mathTextView.setText(String.valueOf(resultNumber));
    }
    public void squareRootNumClick(View view){
        this.resultNumber = calculator(resultNumber, inputNumber, operator);
        activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
        activityMainBinding.mathTextView.setText(String.valueOf(resultNumber));
    }

    private double calculator(double resultNumber, double inputNumber, String operator) {
        switch (operator){
            case "＝":
                resultNumber = inputNumber;
                break;
            case "＋":
                resultNumber = resultNumber + inputNumber;
                break;
            case "－":
                resultNumber = resultNumber - inputNumber;
                break;
            case "×":
                resultNumber = resultNumber * inputNumber;
                break;
            case "÷":
                resultNumber = resultNumber / inputNumber;
                break;
            case "%":
                resultNumber = resultNumber / 100;
            case "1/x":
                resultNumber = 1/resultNumber;
            case "x^2":
                resultNumber = resultNumber * resultNumber;
            case "x^-2":
                resultNumber = Math.sqrt(resultNumber);
            case "+-":
                resultNumber = -1 * resultNumber;
            default:
                Log.e("calculator", resultNumber+ " " + inputNumber + " " + operator);
        }
        return resultNumber;
    }


}