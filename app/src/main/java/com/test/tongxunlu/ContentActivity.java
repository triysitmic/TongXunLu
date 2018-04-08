package com.test.tongxunlu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zhengyanda on 2018/4/4.
 */

public class ContentActivity extends Activity implements View.OnClickListener {

    private MyItemView nameView;

    private MyItemView phoneView;

    private MyItemView messageView;

    private MyItemView emialView;

    private MyItemView addressView;

    private LinearLayout bottomView;

    private TextView quitView;

    private TextView confirmView;

    private TextView deleteView;

    private TextView editView;

    private int type;

    private Person mPerson;

    private boolean isEdited = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        type = getIntent().getIntExtra(MainActivity.TYPE, 0);

        initialView();
    }


    private void initialView() {

        nameView = findViewById(R.id.name);
        nameView.setTitle("姓名");

        phoneView = findViewById(R.id.phone);
        phoneView.setTitle("电话");

        messageView = findViewById(R.id.message);
        messageView.setTitle("简介");

        emialView = findViewById(R.id.email);
        emialView.setTitle("邮件");

        addressView = findViewById(R.id.address);
        addressView.setTitle("地址");

        bottomView = findViewById(R.id.bar);

        quitView = findViewById(R.id.quit);
        quitView.setOnClickListener(this);

        confirmView = findViewById(R.id.confirm);
        confirmView.setOnClickListener(this);

        deleteView = findViewById(R.id.del);
        deleteView.setOnClickListener(this);

        editView = findViewById(R.id.edit);
        editView.setOnClickListener(this);

        if (type == MainActivity.REQUEST_CODE_ADD) {
            bottomView.setVisibility(View.GONE);
        } else if (type == MainActivity.REQUEST_CODE_LOOKOVER) {
            mPerson = (Person) getIntent().getSerializableExtra(MainActivity.PERSON);
            nameView.setContent(mPerson.getName());

            phoneView.setContent(mPerson.getPhoneNumber());

            messageView.setContent(mPerson.getMessage());

            emialView.setContent(mPerson.getEmail());

            addressView.setContent(mPerson.getAddress());

            setUnEditAble();
            confirmView.setVisibility(View.GONE);

        }
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent();
        switch (view.getId()) {
            case R.id.quit:
                i.putExtra(MainActivity.RESORT, MainActivity.QUIT);
                setResult(MainActivity.REQUEST_CODE_ADD, i);
                finishActivity();

                break;
            case R.id.confirm:
                if (checkEdit()) {
                    Person p = new Person(nameView.getContent(),
                            phoneView.getContent(), messageView.getContent(),
                            emialView.getContent(), addressView.getContent());
                    if (!isEdited) {
                        i.putExtra(MainActivity.PERSON, p);
                        i.putExtra(MainActivity.RESORT, MainActivity.ADD_SUCCESS);
                        setResult(MainActivity.REQUEST_CODE_ADD, i);
                    } else {
                        if (p.equals(mPerson)) {
                            i.putExtra(MainActivity.RESORT, MainActivity.QUIT);
                            setResult(MainActivity.REQUEST_CODE_ADD, i);
                        } else {
                            i = getIntent();
                            i.putExtra(MainActivity.PERSON, p);
                            i.putExtra(MainActivity.RESORT, MainActivity.MODIFY_SUCCESS);
                            setResult(MainActivity.MODIFY_SUCCESS, i);
                        }
                    }

                    finishActivity();
                } else {
                    return;
                }
                break;


            case R.id.del:
                i = getIntent();
                i.putExtra(MainActivity.RESORT, MainActivity.DELETE_SUCCESS);
                setResult(MainActivity.REQUEST_CODE_LOOKOVER, i);
                finishActivity();
                break;
            case R.id.edit:
                isEdited = true;
                setEditAble();
                confirmView.setVisibility(View.VISIBLE);
                bottomView.setVisibility(View.GONE);
                break;
        }
    }

    private void finishActivity() {
        this.finish();
    }

    private boolean checkEdit() {
        if (nameView.getContent().equals("") || phoneView.getContent().equals("")) {
            Toast.makeText(this, "请输入联系人姓名和电话", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setUnEditAble() {
        nameView.setContentUnEditable();
        phoneView.setContentUnEditable();
        messageView.setContentUnEditable();
        emialView.setContentUnEditable();
        addressView.setContentUnEditable();
    }

    private void setEditAble() {
        phoneView.setContentEditable();
        messageView.setContentEditable();
        emialView.setContentEditable();
        addressView.setContentEditable();

        nameView.setContentEditable();
    }
}
