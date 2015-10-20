package com.demo.linhao.heightcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button calcularorButton;
    private EditText weightEditText;
    private RadioButton man;
    private RadioButton lady;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        calcularorButton = (Button)findViewById(R.id.cal);
        weightEditText = (EditText)findViewById(R.id.weight);
        man = (RadioButton)findViewById(R.id.man);
        lady = (RadioButton)findViewById(R.id.lady);
        resultTextView = (TextView)findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册事件
        registerEvent();
    }
    private void registerEvent(){
        calcularorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weightEditText.getText().toString().trim().equals("")) {
                    if (man.isChecked() || lady.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("-----评估结果------\n");
                        if (man.isChecked()) {
                            sb.append("男性标准身高");
                            //执行运算
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "厘米");
                        }
                        if (lady.isChecked()) {
                            sb.append("女性标准身高");
                            //执行运算
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "厘米");
                        }
                        //输出页面显示结果
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage("请选择性别");
                    }
                } else {
                    showMessage("请输入体重");
                }
            }
        });
    }
    //执行运算代码部分
    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    //消息提醒
    private void showMessage(String message){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
    //创建菜单
//    public boolean onCreateOptionMenu(Menu menu){
//        menu.add(Menu.NONE, 1, Menu.NONE, "退出");
//        return super.onCreateOptionsMenu(menu);
//    }
    //菜单事件
//    public boolean onOptionItemsSelected(MenuItem item){
//        switch (item.getItemId()){
//            case 1://退出;
//                finish();break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
