package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected int leftNumber;
    protected int rightNumber;
    protected Operator calcOperator;

    protected enum Operator {
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        NOT_DEFINED(""), ;

        private String label;

        Operator(String _label) {
            this.label = _label;
        }

        public String GetLabel() {
            return label;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.number_0).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.number_1).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.number_2).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.number_3).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.number_4).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.number_5).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.number_6).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.number_7).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.number_8).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.number_9).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.backspace).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.operator_plus).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.operator_minus).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.operator_multiply).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.operator_divide).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.calculate).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.clear).setOnClickListener((View.OnClickListener) this);

        leftNumber = 0;
        rightNumber = 0;
        calcOperator = Operator.NOT_DEFINED;

        UpdateFormula();
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.calculate:
                    ((TextView)findViewById(R.id.result)).setText(GetResult());
                    break;

                case R.id.operator_plus:
                    SetOperator(Operator.PLUS);
                    break;

                case R.id.operator_minus:
                    SetOperator(Operator.MINUS);
                    break;

                case R.id.operator_multiply:
                    SetOperator(Operator.MULTIPLY);
                    break;

                case R.id.operator_divide:
                    SetOperator(Operator.DIVIDE);

                case R.id.number_0:
                    SetNumber(0);
                    break;

                case R.id.number_1:
                    SetNumber(1);
                    break;

                case R.id.number_2:
                    SetNumber(2);
                    break;

                case R.id.number_3:
                    SetNumber(3);
                    break;

                case R.id.number_4:
                    SetNumber(4);
                    break;

                case R.id.number_5:
                    SetNumber(5);
                    break;

                case R.id.number_6:
                    SetNumber(6);
                    break;

                case R.id.number_7:
                    SetNumber(7);
                    break;

                case R.id.number_8:
                    SetNumber(8);
                    break;

                case R.id.number_9:
                    SetNumber(9);
                    break;
                case R.id.backspace:
                    BackSpace();
                    break;

                case R.id.clear:
                    Clear();
                    break;

                default:
                    break;
            }
        }
    }

    protected String GetResult() {
        if (calcOperator == Operator.NOT_DEFINED) {
            return "Invalid operator";
        }

        switch (calcOperator) {
            case PLUS:
                return Integer.toString(leftNumber + rightNumber);
            case MINUS:
                return Integer.toString(leftNumber - rightNumber);
            case MULTIPLY:
                return Integer.toString(leftNumber * rightNumber);
            case DIVIDE:
                return Integer.toString(leftNumber / rightNumber);
            default:
                return "0";
        }
    }

    protected void SetOperator(Operator operator) {
        if (calcOperator != Operator.NOT_DEFINED) {
            ((TextView)findViewById(R.id.result)).setText("Cannot set operator twice");
        }

        calcOperator = operator;
        UpdateFormula();
    }

    protected void SetNumber(int number) {
        if (calcOperator == Operator.NOT_DEFINED) {
            leftNumber = Integer.parseInt(Integer.toString(leftNumber) + Integer.toString(number));
        }
        else
        {
            rightNumber = Integer.parseInt(Integer.toString(rightNumber) + Integer.toString(number));
        }

        UpdateFormula();
    }

    protected void BackSpace() {
        if (calcOperator == Operator.NOT_DEFINED) {
            if (leftNumber < 10) {
                leftNumber = 0;
            } else {
                String leftNumber_str = Integer.toString(leftNumber);
                leftNumber = Integer.parseInt(leftNumber_str.substring(0, leftNumber_str.length() - 1));
            }

        } else {
            if (rightNumber < 10) {
                rightNumber = 0;
            }
            else {
                String rightNumber_str = Integer.toString(rightNumber);
                rightNumber = Integer.parseInt(rightNumber_str.substring(0, rightNumber_str.length() - 1));
            }
        }

        UpdateFormula();
    }

    protected void UpdateFormula() {
        if (calcOperator == Operator.NOT_DEFINED)
            ((TextView)findViewById(R.id.formula)).setText(Integer.toString(leftNumber));
        else
            ((TextView)findViewById(R.id.formula)).setText(Integer.toString(leftNumber) + calcOperator.GetLabel() + Integer.toString(rightNumber));
    }

    protected void Clear() {
        leftNumber = 0;
        rightNumber = 0;
        calcOperator = Operator.NOT_DEFINED;
        UpdateFormula();
    }
}