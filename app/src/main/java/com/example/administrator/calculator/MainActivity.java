package com.example.administrator.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends Activity {

    private TextView tv_input;
    private RadioButton rb_2;
    private RadioButton rb_8;
    private RadioButton rb_10;
    private RadioButton rb_16;
    private Button btn_16_A;
    private Button btn_16_B;
    private Button btn_16_C;
    private Button btn_16_D;
    private Button btn_16_E;
    private Button btn_16_F;
    private Button btn_clean;
    private Button btn_delete;
    private Button btn_divide;
    private Button btn_multiply;
    private Button btn_add;
    private Button btn_minus;
    private Button btn_point;
    private Button btn_equal;
    private Button btn_num_0;
    private Button btn_num_1;
    private Button btn_num_2;
    private Button btn_num_3;
    private Button btn_num_4;
    private Button btn_num_5;
    private Button btn_num_6;
    private Button btn_num_7;
    private Button btn_num_8;
    private Button btn_num_9;
    private RadioGroup rg;
    private LinearLayout linlayout_16;
    private TextView tv_output;
    private int flag = 10;// 进制标志位
    private int prevFlag = 10;//前一种进制标识
    private String[] numbers;//存储运算数值
    private String[] chars;//存储运算符
    private String[] sums;//存储数值与运算符组合的运算式
    private String input = "0";
    private String output = null;

    // private List<Integer> numbers = new ArrayList<Integer>();//存放字符串解析得到的數字
    // private List<String> numberchars = new
    // ArrayList<String>();//存放字符串解析得到的运算符

    private void findViewById() {

        rg = (RadioGroup) findViewById(R.id.rg);
        linlayout_16 = (LinearLayout) findViewById(R.id.linlayout_16);
        tv_output = (TextView) findViewById(R.id.tv_output);
        tv_input = (TextView) findViewById(R.id.tv_input);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_8 = (RadioButton) findViewById(R.id.rb_8);
        rb_10 = (RadioButton) findViewById(R.id.rb_10);
        rb_16 = (RadioButton) findViewById(R.id.rb_16);
        btn_16_A = (Button) findViewById(R.id.btn_16_A);
        btn_16_B = (Button) findViewById(R.id.btn_16_B);
        btn_16_C = (Button) findViewById(R.id.btn_16_C);
        btn_16_D = (Button) findViewById(R.id.btn_16_D);
        btn_16_E = (Button) findViewById(R.id.btn_16_E);
        btn_16_F = (Button) findViewById(R.id.btn_16_F);
        btn_clean = (Button) findViewById(R.id.btn_clean);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_num_0 = (Button) findViewById(R.id.btn_num_0);
        btn_num_1 = (Button) findViewById(R.id.btn_num_1);
        btn_num_2 = (Button) findViewById(R.id.btn_num_2);
        btn_num_3 = (Button) findViewById(R.id.btn_num_3);
        btn_num_4 = (Button) findViewById(R.id.btn_num_4);
        btn_num_5 = (Button) findViewById(R.id.btn_num_5);
        btn_num_6 = (Button) findViewById(R.id.btn_num_6);
        btn_num_7 = (Button) findViewById(R.id.btn_num_7);
        btn_num_8 = (Button) findViewById(R.id.btn_num_8);
        btn_num_9 = (Button) findViewById(R.id.btn_num_9);


    }

    int btnNum[] = {R.id.btn_num_2, R.id.btn_num_3, R.id.btn_num_4,
            R.id.btn_num_5, R.id.btn_num_6, R.id.btn_num_7, R.id.btn_num_8,
            R.id.btn_num_9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // ViewUtils.inject(this); //注入view和事件
        findViewById();
        shield_abdef();
        tv_input.setText(input);

        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                switch (arg1) {
                    case R.id.rb_2:// 选中二进制输入
                        btn_num_0.setEnabled(true);
                        btn_num_1.setEnabled(true);
                        shield_abdef();
                        // 屏蔽数字2-9键
                        for (int i = 0; i < btnNum.length; i++) {
                            findViewById(btnNum[i]).setEnabled(false);
                        }
                        btn_point.setEnabled(false);
                        if (input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")) {
                            input = "0";
                            prevFlag = 2;
                            tv_input.setText(input);
                        } else {
                            prevFlag = flag;
                        }
                        flag = 2;
                        break;
                    case R.id.rb_8:// 选中八进制输入
                        // 恢复数字2-9键
                        for (int i = 0; i < btnNum.length; i++) {
                            findViewById(btnNum[i]).setEnabled(true);
                        }
                        btn_num_0.setEnabled(true);
                        btn_num_1.setEnabled(true);
                        shield_abdef();
                        btn_num_9.setEnabled(false);
                        btn_num_8.setEnabled(false);
                        btn_point.setEnabled(false);
                        if (input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")) {
                            input = "0";
                            prevFlag = 8;
                            tv_input.setText(input);
                        } else {
                            prevFlag = flag;
                        }
                        flag = 8;
                        break;
                    case R.id.rb_10:// 选中十进制输入
                        btn_num_0.setEnabled(true);
                        btn_num_1.setEnabled(true);
                        for (int i = 0; i < btnNum.length; i++) {
                            findViewById(btnNum[i]).setEnabled(true);
                        }
                        btn_point.setEnabled(true);
                        shield_abdef();
                        if (input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")) {
                            input = "0";
                            prevFlag = 10;
                            tv_input.setText(input);
                        } else {
                            prevFlag = flag;
                        }
                        flag = 10;
                        break;
                    case R.id.rb_16:// 选中十六进制输入
                        // 恢复十六进制ABCDEF键
                        recover_abdef();
                        // 恢复数字2-9键
                        for (int i = 0; i < btnNum.length; i++) {
                            findViewById(btnNum[i]).setEnabled(true);
                        }
                        btn_num_0.setEnabled(true);
                        btn_num_1.setEnabled(true);
                        btn_point.setEnabled(false);
                        if (input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")) {
                            input = "0";
                            prevFlag = 16;
                            tv_input.setText(input);
                        } else {
                            prevFlag = flag;
                        }
                        flag = 16;
                        break;
                    default:
                        break;
                }
            }

        });
        numbers = input.split("[-+*/]");
        chars = input.split("[0-9A-F.]+");
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_num_0:
                if (input.charAt(input.length() - 1) == '/') {
                    Toast.makeText(MainActivity.this, "除数不能为零", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    input += "0";
                    tv_input.setText(input);
                }
                break;
            case R.id.btn_num_1:
                replace("1");
                tv_input.setText(input);
                break;
            case R.id.btn_num_2:
                replace("2");
                tv_input.setText(input);
                break;
            case R.id.btn_num_3:
                replace("3");
                tv_input.setText(input);
                break;
            case R.id.btn_num_4:
                replace("4");
                tv_input.setText(input);
                break;
            case R.id.btn_num_5:
                replace("5");
                tv_input.setText(input);
                break;
            case R.id.btn_num_6:
                replace("6");
                tv_input.setText(input);
                break;
            case R.id.btn_num_7:
                replace("7");
                tv_input.setText(input);
                break;
            case R.id.btn_num_8:
                replace("8");
                tv_input.setText(input);
                break;
            case R.id.btn_num_9:
                replace("9");
                tv_input.setText(input);
                break;
            case R.id.btn_16_A:
                replace("A");
                tv_input.setText(input);
            case R.id.btn_16_B:
                replace("B");
                tv_input.setText(input);
                break;
            case R.id.btn_16_C:
                replace("C");
                tv_input.setText(input);
                break;
            case R.id.btn_16_D:
                replace("D");
                tv_input.setText(input);
                break;
            case R.id.btn_16_E:
                replace("E");
                tv_input.setText(input);
                break;
            case R.id.btn_16_F:
                replace("F");
                tv_input.setText(input);
                break;
            case R.id.btn_add:// 填入加符号
                char operator0 = '+';
                judge_operator(operator0);
                tv_input.setText(input);
                break;
            case R.id.btn_minus:// 填入减符号
                char operator1 = '-';
                judge_operator(operator1);
                tv_input.setText(input);
                break;
            case R.id.btn_multiply:// 填入乘符号
                char operator2 = '*';
                judge_operator(operator2);
                tv_input.setText(input);
                break;
            case R.id.btn_divide:// 填入除以符号
                char operator3 = '/';
                judge_operator(operator3);
                tv_input.setText(input);
                break;
            case R.id.btn_point:// 填入小数点符号
                char operator4 = '.';
                operator_Point(operator4);
                tv_input.setText(input);
                break;
            case R.id.btn_clean:// 清零
                input = "0";
                prevFlag = flag;
                Toast.makeText(MainActivity.this, "prevFlag=" + prevFlag, Toast.LENGTH_SHORT).show();
                tv_input.setText(input);
                tv_output.setText(input);
                break;
            case R.id.btn_delete:// 删除末位
                if (input.length() == 0) {
                    input = "0";
                } else {
                    input = input.substring(0, input.length() - 1);
                    if (input.length() == 0) {
                        input = "0";
                    }
                }
                tv_input.setText(input);
                break;
            case R.id.btn_equal:
                numbers = input.split("[-+*/]");
                chars = input.split("[0-9A-F.]+");
                if (flag == prevFlag) {
                    if(chars.length==0){
                        output = numbers[0];
                    }else{
                        getResult(prevFlag);
                    }
                }else{
                    getResult(prevFlag);
                }
                tv_output.setText(output);
                break;
            default:
                break;
        }

    }

    /**
     * 根据进制得到结果
     */
    private void getResult(int prevFlag) {
        switch (flag) {
            case 2:
                String countArray2[];
                countArray2 = getArray(numbers, chars, prevFlag);
                double dou2 = compute(countArray2);
                int tem2 = (int) dou2;
                String result2 = Integer.toBinaryString(tem2);
                output = result2;
                break;
            case 10:
                String countArray10[];
                countArray10 = getArray(numbers, chars, prevFlag);
                double dou10 = compute(countArray10);
                output = dou10+"";
                break;

            case 8:

                String countArray8[];
                countArray8 = getArray(numbers, chars, prevFlag);
                double dou8 = compute(countArray8);
                int tem8 = (int) dou8;
                String result8 = Integer.toOctalString(tem8);
                output = result8;
                break;

            case 16:

                String countArray16[];
                countArray16 = getArray(numbers, chars, prevFlag);
                double dou16 = compute(countArray16);
                int tem16 = (int) dou16;
                String result16 = Integer.toHexString(tem16);
                output = result16;
                break;

            default:
                break;
        }
    }

    // 添加小数点
    public void operator_Point(char b) {
        String str = "+-*/";
        if (input.length() == 0) {// 输入运算符时，判断前面是否有数字，没有则填0并加上运算符
            input = "0" + b;
        } else {
            boolean bool = str.contains(input.substring(input.length() - 1));
            if (bool) {
                String temp = input;
                input = temp + "0" + b;
            } else if (input.charAt(input.length() - 1) == '.') {
                Toast.makeText(MainActivity.this, "input error", Toast.LENGTH_SHORT).show();
            } else {
                input += b;
            }
        }
    }

    // 在输入运算符时判断末尾是数字还是运算符（1.运算符时替换，2.不是运算符，添加符号）
    public void judge_operator(char c) {
        String str = "+-*/.";
        if (input.length() == 0) {// 输入运算符时，判断前面是否有数字，没有则填0并加上运算符
            input = "0" + c;
        } else {
            boolean b = str.contains(input.substring(input.length() - 1));
            if (!b) {
                input += c;
            } else {
                StringBuffer strBuf = new StringBuffer(input);
                strBuf.replace(input.length() - 1, input.length(), c + "");
                input = strBuf.toString();
            }
        }

    }

    // 遍历父布局的子节点,屏蔽abcdef键
    public void shield_abdef() {
        for (int i = 0; i < linlayout_16.getChildCount(); i++) {
            View v = linlayout_16.getChildAt(i);
            Button button = (Button) v;
            button.setEnabled(false);
        }
    }

    // 遍历父布局的子节点,恢复abcdef键
    public void recover_abdef() {
        for (int i = 0; i < linlayout_16.getChildCount(); i++) {
            View v = linlayout_16.getChildAt(i);
            Button button = (Button) v;
            button.setEnabled(true);
        }
    }

    //添加数值
    public void replace(String numStr) {
        if (input.equals("0")) {// 替换首位数字0
            input = numStr;
        } else {
            input += numStr;
        }
    }

    /**
     * 得到运算式的数组形式
     *
     * @param numbers 数值数组(十进制数值)
     * @param chars   运算符数组
     * @return
     */
    private String[] getArray(String[] numbers, String[] chars, int sys) {
        String sums[] = null;
        String[] ch;
        if (chars.length == 0) {
            sums = new String[numbers.length];
            switch (sys) {
                default:
                    sums[0] = numbers[0];
                    break;
                case 2:
                    String temp2;
                    temp2 = Integer.valueOf(numbers[0], sys).toString();
                    sums[0] = temp2;
                    break;
                case 8:
                    String temp8;
                    temp8 = Integer.valueOf(numbers[0], sys).toString();
                    sums[0] = temp8;
                    break;
                case 16:
                    String temp16;
                    temp16 = Integer.valueOf(numbers[0], sys).toString();
                    sums[0] = temp16;
                    break;
            }
        } else if (chars.length == numbers.length) {//chars.length>=2
            ch = new String[chars.length - 1];
            for (int i = 1; i < chars.length; i++) {
                ch[i - 1] = chars[i];
            }
            sums = groupSums(numbers, ch, sys);
        } else if (chars.length > numbers.length) {//"65+"   "65+98+"
            ch = new String[chars.length - 2];
            for (int i = 1; i < chars.length - 1; i++) {
                ch[i - 1] = chars[i];
            }

            sums = groupSums(numbers, ch, sys);

        }
        return sums;
    }

    /**
     * 组合数组
     * 根据输入进制类型，其他进制类型转成十进制数组
     *
     * @param numbers 数值数组 （二进制 八进制 十进制 十六进制）
     * @param ch      去除空格和多余运算符的数组
     * @return 组合后的运算符数组
     */
    private String[] groupSums(String[] numbers, String[] ch, int sys) {
        String[] sums;
        sums = new String[numbers.length + ch.length];
        for (int i = 0, j = 0; j < ch.length; i += 2, j++) {
            switch (sys) {
                default:
                    sums[i] = numbers[j];
                    break;
                case 2:
                    String temp2;
                    temp2 = Integer.valueOf(numbers[j], sys).toString();
                    sums[i] = temp2;
                    break;
                case 8:
                    String temp8;
                    temp8 = Integer.valueOf(numbers[j], sys).toString();
                    sums[i] = temp8;
                    break;
                case 16:
                    String temp16;
                    temp16 = Integer.valueOf(numbers[j], sys).toString();
                    sums[i] = temp16;
                    break;
            }
            sums[i + 1] = ch[j];
        }
        switch (sys) {
            default:
                sums[numbers.length + ch.length - 1] = numbers[numbers.length - 1];
                break;
            case 2:
                String temp2;
                temp2 = Integer.valueOf(numbers[numbers.length - 1], sys).toString();
                sums[numbers.length + ch.length - 1] = temp2;
                break;
            case 8:
                String temp8;
                temp8 = Integer.valueOf(numbers[numbers.length - 1], sys).toString();
                sums[numbers.length + ch.length - 1] = temp8;
                break;
            case 16:
                String temp16;
                temp16 = Integer.valueOf(numbers[numbers.length - 1], sys).toString();
                sums[numbers.length + ch.length - 1] = temp16;
                break;
        }
        return sums;
    }


    // 四则运算返回结果
    private double compute(String[] str) {
        Stack<Double> s = new Stack<>();
        double m = Double.parseDouble(str[0]);
        s.push(m);
        for (int i = 0; i < str.length; i++) {
            if (i % 2 == 1) {
                if (str[i].compareTo("+") == 0) {
                    double help = Double.parseDouble(str[i + 1]);
                    s.push(help);
                }
                if (str[i].compareTo("-") == 0) {
                    double help = Double.parseDouble(str[i + 1]);
                    s.push(-help);
                }
                if (str[i].compareTo("*") == 0) {
                    double help = Double.parseDouble(str[i + 1]);
                    double ans = s.peek();// 取出栈顶元素
                    s.pop();// 消栈
                    ans *= help;
                    s.push(ans);
                }
                if (str[i].compareTo("/") == 0) {
                    double help = Double.parseDouble(str[i + 1]);
                    double ans = s.peek();// 取出栈顶元素
                    s.pop();// 消栈
                    ans /= help;
                    s.push(ans);
                }
            }
        }
        double ans = 0d;
        while (!s.isEmpty()) {
            ans += s.peek();
            s.pop();
        }
        // String result = String.valueOf(ans);
        return ans;
    }

}
