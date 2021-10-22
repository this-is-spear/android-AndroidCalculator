package com.example.androidcalculator_201512122;

public class DefaultCalculator {
    // 입력한 숫자가 첫 번째로 입력한 것인지 아닌지 확인해야 한다.
    // 처음 실행해서 숫자를 넣으면 첫 번째 숫자이기 때문이다.
    boolean isFirstInput = true;
    boolean isOperatorClick = false;
    double inputNumber = 0;
    double resultNumber = 0;
    // 초기 값은 ＝로 초기화 ㄷ+'한자' 키 조합을 만듬
    String operator = "＝";
    String lastOperator = "＋";

    public DefaultCalculator() {
    }

    public void setFirstInput(boolean firstInput) {
        isFirstInput = firstInput;
    }

    public void setOperatorClick(boolean operatorClick) {
        isOperatorClick = operatorClick;
    }

    public void setInputNumber(double inputNumber) {
        this.inputNumber = inputNumber;
    }

    public void setResultNumber(double resultNumber) {
        this.resultNumber = resultNumber;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    public boolean isFirstInput() {
        return isFirstInput;
    }

    public boolean isOperatorClick() {
        return isOperatorClick;
    }

    public double getInputNumber() {
        return inputNumber;
    }

    public double getResultNumber() {
        return resultNumber;
    }

    public String getOperator() {
        return operator;
    }

    public String getLastOperator() {
        return lastOperator;
    }
}
