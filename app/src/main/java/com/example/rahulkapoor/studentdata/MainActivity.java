package com.example.rahulkapoor.studentdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.rahulkapoor.studentdata.adapter.RecycleItems;
import com.example.rahulkapoor.studentdata.model.StudentInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * this is the main activity and is called first;
 */
public class MainActivity extends AppCompatActivity {

    private static int requestCode = 2;
    private Button button;
    private Button button1;
    private Button button2;
    private Button buttonsortid;
    private Button buttonsortname;
    private Intent intent;
    private String mname;
    private String mrollno;
    private boolean switchState;
    private ArrayList<StudentInfo> studentInfoArrayList = new ArrayList<>();
    private LinearLayoutManager managerlinear;
    private GridLayoutManager managergrid;
    private RecycleItems recycleItemsobj;

    /**
     *
     * @param savedInstanceState Instance state is saved;
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnclick);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {

                intent = new Intent(MainActivity.this, StudentData.class);
                startActivityForResult(intent, requestCode);

            }
        });

        button1 = (Button) findViewById(R.id.btnsortrollno);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {

                Collections.sort(studentInfoArrayList, new Comparator<StudentInfo>() {
                    @Override
                    public int compare(final StudentInfo o1, final StudentInfo o2) {
                        return o1.getRollno().compareTo(o2.getRollno());
                    }
                });

            }
        });

        button2 = (Button) findViewById(R.id.btnsortname);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {

                Collections.sort(studentInfoArrayList, new Comparator<StudentInfo>() {
                    @Override
                    public int compare(final StudentInfo o1, final StudentInfo o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });

            }
        });


    }

    /**
     *
     * @param requestCode request code used in intent for result;
     * @param resultCode  result code
     * @param intent    to jump to other activity and get data from it intent is used;
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                StudentInfo obj = intent.getParcelableExtra("infoobject");

                studentInfoArrayList.add(obj);

                RecyclerView rvinfostudent = (RecyclerView) findViewById(R.id.rv_items);

                Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);

                switchState = simpleSwitch.isChecked();

                if (switchState) {
                    managerlinear = new LinearLayoutManager(this);
                    RecycleItems adapter = new RecycleItems(this, studentInfoArrayList);
                    rvinfostudent.setAdapter(adapter);
                    rvinfostudent.setLayoutManager(new LinearLayoutManager(this));
                    adapter.notifyDataSetChanged();

                } else {
                    managergrid = new GridLayoutManager(this, 2);
                    RecycleItems adapter = new RecycleItems(this, studentInfoArrayList);
                    rvinfostudent.setAdapter(adapter);
                    rvinfostudent.setLayoutManager(managergrid);
                    adapter.notifyDataSetChanged();
                }
            }

        } else {
            Toast.makeText(this, "No Request Code Found.", Toast.LENGTH_LONG).show();
        }

    }


}




