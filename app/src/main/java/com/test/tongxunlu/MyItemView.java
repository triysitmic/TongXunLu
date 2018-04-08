package com.test.tongxunlu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zhengyanda on 2018/4/4.
 */

public class MyItemView extends LinearLayout {

    private TextView title;

    private EditText content;

    public MyItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.content,this);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

    }

    public void setTitle(String t){
        title.setText(t);
    }
    public void setContent(String c){
        content.setText(c);
    }


    public String getContent(){
        return content.getText().toString();
    }

    public void setContentUnEditable(){
        content.setFocusable(false);
    }
    public void setContentEditable(){
        content.setFocusable(true);
        content.setFocusableInTouchMode(true);
        content.requestFocus();

        content.setSelection(content.getText().toString().length());
    }
}
