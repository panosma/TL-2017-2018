package exnologialogismikou.tei.com.bestoffer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {


    private DatabaseReference mFirebaseDatabaseReference;
    private static final String TAG = "LoginActivity";

    EditText editText_email;
    EditText editText_password;
    Button button_Log_in;
    Button button_Log_out;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        button_Log_in = findViewById(R.id.button_log_in);
        button_Log_out = findViewById(R.id.button_Log_out);
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "Sign in" + user.getUid());
                    toastMessage("Successfully signed in with:" + user.getEmail());
                }
                else
                {
                    Log.w(TAG, "Sign out");
                    toastMessage("Successfully signed out");
                }
            }
        };

        button_Log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_email.getText().toString();
                String psw = editText_password.getText().toString();

                if (!email.equals("") && !psw.equals("")) {
                    firebaseAuth.signInWithEmailAndPassword(email, psw);
                    startActivity(new Intent(LoginActivity.this,Info.class));
                } else {
                    toastMessage("You didn't fill in all the fields!");
                }
            }
        });

        button_Log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                toastMessage("Signing out...");
                startActivity(new Intent(LoginActivity.this,User_selection.class));
            }
        });
    }

    //Toast.makeText(LoginActivity.this, "You didn't fill in all the fields!", Toast.LENGTH_LONG).show();

    private void toastMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }
}








