package com.example.rahulkapoor.studentdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.rahulkapoor.studentdata.model.StudentInfo;

/**
 * activity that checks for validation of our data;
 */
public class StudentData extends AppCompatActivity {

    private StudentInfo studentInfoobj;
    private StudentInfo objectStudentInfo;
    private String mname;
    private String mschool;
    private String mrollno;
    private String mEmail;
    private boolean mchecked;
    private Button button;
    private Intent mintent, mintentpass;
    private EditText editname, editschool, editrollno, editEmail;

    /**
     *
     * @param savedInstanceState instance state is saved
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);

        init();


        button = (Button) findViewById(R.id.btnsubmit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                boolean b = checkValidation();

                if (b) {
                    mintent = new Intent();
                    studentInfoobj = new StudentInfo(mname, mschool, mrollno, mEmail);
                    mintent.putExtra("infoobject", studentInfoobj);
                    setResult(RESULT_OK, mintent);
                    finish();
                } else {
                    Toast.makeText(StudentData.this, "enter details again!", Toast.LENGTH_LONG).show();
                }

            }
        });

        if (objectStudentInfo != null) {
            if ("edit".equals(mintentpass.getStringExtra("key"))) {
                button = (Button) findViewById(R.id.btnsubmit);
                button.setText("Submit New Data");
                modifyData();

            } else if ("view".equals(mintentpass.getStringExtra("key"))) {
                button = (Button) findViewById(R.id.btnsubmit);
                button.setVisibility(View.GONE);
                modifyData();
                editname.setEnabled(false);
                editEmail.setEnabled(false);
                editschool.setEnabled(false);
                editrollno.setEnabled(false);

            }

        }


    }

    /**
     * data is set here in the form fields;
     */
    private void modifyData() {

        editname = (EditText) findViewById(R.id.etname);
        editname.setText(objectStudentInfo.getName());

        editschool = (EditText) findViewById(R.id.etschool);
        editschool.setText(objectStudentInfo.getSchool());

        editrollno = (EditText) findViewById(R.id.etrollno);
        editrollno.setText(objectStudentInfo.getRollno());

        editEmail = (EditText) findViewById(R.id.etEmail);
        editEmail.setText(objectStudentInfo.getEmail());

    }

    /**
     * object from intent is saved here;
     */
    private void init() {
        mintentpass = new Intent();
        objectStudentInfo = mintentpass.getParcelableExtra("keyobject");

    }

    /**
     *
     * @return all entered form fields are checked;
     */
    private boolean checkValidation() {

        EditText viewname = (EditText) findViewById(R.id.etname);
        mname = viewname.getText().toString();
        EditText viewschool = (EditText) findViewById(R.id.etschool);
        mschool = viewschool.getText().toString();
        EditText viewrollno = (EditText) findViewById(R.id.etrollno);
        mrollno = viewrollno.getText().toString();
        EditText viewEmail = (EditText) findViewById(R.id.etEmail);
        mEmail = viewEmail.getText().toString();

        if (mname.isEmpty() || mschool.isEmpty() || mrollno.isEmpty()) {
            if (isValidEmail(mEmail)) {
                return true;
            }
            return false;
        }

        return true;

    }

    /**
     *
     * @param email email validation is checked
     * @return boolean is returned after validating email;
     */
    private boolean isValidEmail(final String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     *
     * @param view radio button checked
     */
    public void onRadioButtonClicked(final View view) {

        mchecked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbmale:
                if (mchecked) {
                    break;
                }
            case R.id.rbfemale:
                if (mchecked) {
                    break;
                }
            default:
                Toast.makeText(this, "check a button first", Toast.LENGTH_LONG).show();
                break;
        }
    }


}
