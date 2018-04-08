package com.test.tongxunlu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final int REQUEST_CODE_ADD = 0;
    public static final int REQUEST_CODE_LOOKOVER = 1;

    public static final int ADD_SUCCESS = 0;
    public static final int QUIT = 1;
    public static final int DELETE_SUCCESS = 2;
    public static final int MODIFY_SUCCESS = 3;

    public static final String TYPE = "TYPE";
    public static final String PERSON = "PERSON";
    public static final String RESORT = "RESORT";
    public static final String POSITION = "POSITION";


    private ListView mListView;

    private List<Person> persons = new ArrayList<>();


    private MyAdapter myAdapter;

    private TextView addView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        persons.add(new Person("江", "123412", "+1", "111@xxx.com", "中国"));
        persons.add(new Person("刁", "1111111", "连R10y", "222@xxx.com", "地球"));


        myAdapter = new MyAdapter(this, persons);

        mListView = findViewById(R.id.lv);
        mListView.setAdapter(myAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra(TYPE, REQUEST_CODE_LOOKOVER);
                intent.putExtra(PERSON, persons.get(i));
                intent.putExtra(POSITION, i);
                startActivityForResult(intent, REQUEST_CODE_LOOKOVER);
            }
        });

        addView = findViewById(R.id.confirm);
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, ContentActivity.class);
                mIntent.putExtra(TYPE, REQUEST_CODE_ADD);
                startActivityForResult(mIntent, REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int resort = data.getIntExtra(MainActivity.RESORT, 0);
        switch (requestCode) {
            case REQUEST_CODE_ADD:
                if (resort == MainActivity.ADD_SUCCESS) {
                    Person p = (Person) data.getSerializableExtra(PERSON);
                    if (p != null) {
//                    Log.e("11", p.getName());
                        persons.add(p);
                        myAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case REQUEST_CODE_LOOKOVER:
                if (resort == MainActivity.DELETE_SUCCESS) {
                    int position = data.getIntExtra(MainActivity.POSITION, 0);
//                    Log.e("22", position + "");
                    persons.remove(position);
                    myAdapter.notifyDataSetChanged();
                } else if (resort == MainActivity.MODIFY_SUCCESS) {
                    Person p = (Person) data.getSerializableExtra(MainActivity.PERSON);
                    int position = data.getIntExtra(MainActivity.POSITION, 0);
                    persons.set(position, p);
                    myAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }
}
