package ipproject.com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Locale;

public class SignupActivity extends AppCompatActivity {

    Button login;
    Button signup;
    String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";

    String namePattern = "[a-zA-Z]+";

    EditText name,email,contact,password,confirmpass,dob;

    RadioGroup gender;
    RadioButton male,female;

    Spinner city;

    String[] cityarray={"Ahmedabad","Surat","Vadodara","Rajkot","Bhavnagar","Jamnagar","Gandhinagar","Junagadh","Gandhidham","Anand","Navsari","Morbi","Patan"};

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        contact=findViewById(R.id.contact);
        password=findViewById(R.id.password);
        confirmpass=findViewById(R.id.confirmpass);

        gender=findViewById(R.id.gender);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=findViewById(i);
                new CommonMethod(SignupActivity.this,radioButton.getText().toString());
            }
        });

        city=findViewById(R.id.city);
        ArrayAdapter adapter =new ArrayAdapter(SignupActivity.this,android.R.layout.simple_list_item_checked,cityarray);
        city.setAdapter(adapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                new CommonMethod(SignupActivity.this,cityarray[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dob=findViewById(R.id.dob);

        calendar=Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateClick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR,i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH,i2);
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                dob.setText(sdf.format(calendar.getTime()));
            }
        };

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity.this,dateClick,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
              datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
              datePickerDialog.show();
            }
        });

        signup = findViewById(R.id.signup);
        login =findViewById(R.id.login);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().equals("")){
                    name.setError("Name Required");
                }  else if (!name.getText().toString().trim().matches(namePattern)) {
                    name.setError("Enter valid Name");
                } else if (email.getText().toString().trim().equals("")){
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Enter valid Email ID");
                } else if (contact.getText().toString().trim().equals("")) {
                    contact.setError("Contact No. required");
                } else if (contact.getText().toString().trim().length()<10) {
                    contact.setError("Enter valid Number");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("Password Required");
                } else if (password.getText().toString().trim().length()<6) {
                    password.setError("Min 6 length Required");
                }  else if (confirmpass.getText().toString().trim().equals("")) {
                    confirmpass.setError("Confirm Password Required");
                }  else if (!confirmpass.getText().toString().trim().matches(password.getText().toString())) {
                    confirmpass.setError("Confirm Password Must be same as Password");
                } else if (gender.getCheckedRadioButtonId()==-1) {
                    new CommonMethod(SignupActivity.this,"Select Gender");
                } else if (dob.getText().toString().trim().equals("")) {
                    dob.setError("Birth Date Required ");
                } else {
                    System.out.println("SignUp Successfully");
                    Toast.makeText(SignupActivity.this, "SignedUp Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                    startActivity(intent);
                }

                    }
                });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}