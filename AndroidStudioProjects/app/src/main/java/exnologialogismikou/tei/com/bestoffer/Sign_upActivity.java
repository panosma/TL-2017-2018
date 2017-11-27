package exnologialogismikou.tei.com.bestoffer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_upActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    private  FirebaseAuth firebaseAuth;

    Button Sign_up;



    DatabaseReference EmailRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = EmailRef.child("Email");
    DatabaseReference PwdRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRef = PwdRef.child("Password");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.Password);
        firebaseAuth = FirebaseAuth.getInstance();

        Sign_up = findViewById(R.id.Sign_up);

    }
    public void Sign_up_Click(View v){
        final ProgressDialog progressDialog = ProgressDialog.show(Sign_upActivity.this,"Please wait...","Processing...",true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {

                            Toast.makeText(Sign_upActivity.this, "Sign_up successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Sign_upActivity.this, Home_screen.class);
                            startActivity(i);
                        } else {

                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(Sign_upActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }

                });


    }
}
