package com.ngwiri.flexnany.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ngwiri.flexnany.Network;
import com.ngwiri.flexnany.R;
import com.ngwiri.flexnany.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static View view;
    private static Animation shakeAnimation;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

//    boolean doubleBackToExitPressedOnce = false;



    @BindView(R.id.signUpLayout) LinearLayout mSignUpLayout;
    @BindView(R.id.FirstName) EditText mFirstName;
    @BindView(R.id.LastName) EditText mLastName;
    @BindView(R.id.SignUp_Email) EditText mSignUp_Email;
    @BindView(R.id.Phone_number) EditText mPhone_number;
    @BindView(R.id.Password) EditText mPassword;
    @BindView(R.id.confirm_password) EditText mConfirm_password;
    @BindView(R.id.signUpButton) Button mSignUpButton;
    @BindView(R.id.back_to_login) TextView mBack_to_login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthProgressDialog();

        mSignUpButton.setOnClickListener(this);
        mBack_to_login.setOnClickListener(this);

        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        //<--- CHECKING INTERNET CONNECTION START
        if(Network.isInternetAvailable(SignUpActivity.this)) //returns true if internet available
        {

        }
        else
        {
            new CustomToast().Show_Toast(getApplicationContext(), view,
                    "No Internet Connection");

        }

        //CHECKING INTERNET CONNECTION END --->


    }

    @Override
    public void onClick(View v) {
        if (v == mSignUpButton){
            checkValidation();

        }
        if (v == mBack_to_login){
            Intent intent = new Intent(this , LoginActivity.class);
            startActivity(intent);
            finish();
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
                || getConfirmPassword.equals("") || getConfirmPassword.length() == 0){

           mSignUpLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getApplicationContext(), view,
                    "All fields are required.");
        }



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
            SignInUser();

//   Make sure user should check Terms and Conditions checkbox
// *TO BE USED LATER*
//        else if (!terms_conditions.isChecked())
//            new CustomToast().Show_Toast(getApplicationContext(), view,
//                    "Please select Terms and Conditions.");




    }

    //<--- PROGRESSDIALOG START
    //setCancelable() to "false" so that users cannot close the dialog manually.
    private void createAuthProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading ...");
        progressDialog.setMessage("Please wait while Creating Account");
        progressDialog.setCancelable(false);

    }
    //PROGRESSDIALOG END --->

    private void SignInUser() {

        final String email = mSignUp_Email.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String phone = mPhone_number.getText().toString().trim();
        String first_name = mFirstName.getText().toString().trim();
        String last_name = mLastName.getText().toString().trim();

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();


                        } else {
                            // If sign in fails, display a message to the user.
                            new CustomToast().Show_Toast(getApplicationContext(), view,
                                    "Invalid Credentials.");
                        }
                    }
                });
    }

}
