package com.ngwiri.flexnany;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static View view;

    @BindView(R.id.FirstName) EditText mFirstName;
    @BindView(R.id.LastName) EditText mLastName;
    @BindView(R.id.SignUp_Email) EditText mSignUp_Email;
    @BindView(R.id.Phone_number) EditText mPhone_number;
    @BindView(R.id.Password) EditText mPassword;
    @BindView(R.id.confirm_password) EditText mConfirm_password;
    @BindView(R.id.signUpButton) Button mSignUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public void onClick(View v) {
        if (v == mSignUpButton){
            checkValidation();
        }
    }

    // Check Validation Method
    private void checkValidation() {

        // Get all edittext texts
        String getFirstName = mFirstName.getText().toString();
        String getLastName = mLastName.getText().toString();
        String getEmailId = mSignUp_Email.getText().toString();
        String getMobileNumber = mPhone_number.getText().toString();
        String getPassword = mPassword.getText().toString();
        String getConfirmPassword = mConfirm_password.getText().toString();

        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not
        if (    getFirstName.equals("") || getFirstName.length() == 0
                ||getLastName.equals("") || getLastName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("") || getConfirmPassword.length() == 0)

            new CustomToast().Show_Toast(getApplicationContext(), view,
                    "All fields are required.");

            // Check if email id valid or not
        else if (!m.find())
            new CustomToast().Show_Toast(getApplicationContext(), view,
                    "Your Email Id is Invalid.");

            // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword))
            new CustomToast().Show_Toast(getApplicationContext(), view,
                    "Both password doesn't match.");

            // Else do signup or do your stuff
        else
            Toast.makeText(getApplicationContext(), "Do SignUp.", Toast.LENGTH_SHORT)
                    .show();

//   Make sure user should check Terms and Conditions checkbox
// *TO BE USED LATER*
//        else if (!terms_conditions.isChecked())
//            new CustomToast().Show_Toast(getApplicationContext(), view,
//                    "Please select Terms and Conditions.");




    }
}
