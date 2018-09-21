package test.test.tp3;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class AddStudentActivity extends AppCompatActivity {

    private Button getDate;
    private Button addButton;

    private Date selectedDate;

    private final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_form);
        this.setFinishOnTouchOutside(false);
        setResult(1);

        addButton = (Button)findViewById(R.id.AddStudentFormButton);
        getDate = (Button)findViewById(R.id.PickDateButton);
        getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog picker = new DatePickerDialog(AddStudentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar selected = Calendar.getInstance();
                                selected.set(Calendar.YEAR, year);
                                selected.set(Calendar.MONTH, month);
                                selected.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                selectedDate = selected.getTime();

                                getDate.setText(MainActivity.DATE_FORMAT.format(selected.getTime()));
                            }
                        }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                picker.show();
            }
        });

        addButton.setOnClickListener(new AddButtonClickListener());

        Button cancelButton = (Button)findViewById(R.id.CancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    private class AddButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            EditText lastname = (EditText)findViewById(R.id.LastName);
            EditText firstname = (EditText)findViewById(R.id.FirstName);
            EditText email = (EditText)findViewById(R.id.Email);
            Spinner group = (Spinner)findViewById(R.id.Group);
            Switch redoublant = (Switch)findViewById(R.id.Redoublant);
            RadioGroup sexe = (RadioGroup)findViewById(R.id.Sex);
            RadioButton sexeRadio = (RadioButton)findViewById(sexe.getCheckedRadioButtonId());

            if(selectedDate != null && lastname.getText() != null && firstname.getText() != null && email.getText() != null){
                String emailText = email.getText().toString();
                Pattern pattern = Pattern.compile(EMAIL_REGEX);
                if(pattern.matcher(emailText).matches()) {
                    Student student = new Student(lastname.getText().toString(), firstname.getText().toString(), sexeRadio.getText().toString(), emailText, selectedDate, group.getSelectedItem().toString(), redoublant.isChecked());

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("Student", student);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }else{
                    Toast.makeText(AddStudentActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(AddStudentActivity.this, "Fill all the fields please", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
