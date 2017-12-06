package exnologialogismikou.tei.com.bestoffer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Info extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTextTime;
    private Button buttonEntry;

    public static final String USERS = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextTime = findViewById(R.id.editTextTime);
        buttonEntry = findViewById(R.id.buttonEntry);

        buttonEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get firebase user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                //get reference
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference(USERS);

                Specs specs = new Specs(editTextName.getText().toString(), editTextAddress.getText().toString(), editTextPhone.getText().toString(), editTextTime.getText().toString());

                //build child
                ref.child(user.getUid()).setValue(specs);

            }


        });



    }
}

