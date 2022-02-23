package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText FirstName;
    EditText FamilyName;
    EditText Dob;
    EditText Email;
    EditText Password;
    Button Register;
    TextView AccountExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstName = findViewById(R.id.FirstName);
        FamilyName = findViewById(R.id.FamilyName);
        Dob = findViewById(R.id.DOB);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        AccountExist = findViewById(R.id.btnAccountExist);
        Register = findViewById(R.id.btnRegister);

        //onclick event of button
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkdataentered() && ValidateEmailAddress(Email) && isValidDate(Dob.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
            }
        });
//Added navigatin when click on login
        AccountExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    //function where all values are checked on time of submit button
    boolean checkdataentered() {
        if (isEmpty(FirstName)) {
            Toast t = Toast.makeText(this, "You must enter first name", Toast.LENGTH_SHORT);
            t.show();
            return false;
        } else if (FirstName.length() > 30) {
            Toast t = Toast.makeText(this, "First Name should not be greater than 30 characters", Toast.LENGTH_SHORT);
            t.show();
            return false;
        } else if (FirstName.length() < 3) {
            Toast t = Toast.makeText(this, "First Name should atleast contain 3 characters", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (isEmpty(FamilyName)) {
            Toast t = Toast.makeText(this, "Please enter family name", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }

        if (isEmpty(Dob)) {
            Toast t = Toast.makeText(this, "Please enter DOB", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (isEmpty(Email)) {
            Toast t = Toast.makeText(this, "Please enter email address", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (isEmpty(Password)) {
            Toast t = Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        return true;
    }

    //function to check null values
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    //function to validate email address
    private boolean ValidateEmailAddress(EditText emailId) {
        String EmailInput = emailId.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(EmailInput).matches()) {
            return true;
        } else {
            Toast.makeText(this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    //function to validate date format
    public boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (ParseException e) {
            Toast.makeText(this, "Please enter date in MM/DD/YYYY Format.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!sdf.format(testDate).equals(date)) {
            Toast.makeText(this, "Please enter date in MM/DD/YYYY Format.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}