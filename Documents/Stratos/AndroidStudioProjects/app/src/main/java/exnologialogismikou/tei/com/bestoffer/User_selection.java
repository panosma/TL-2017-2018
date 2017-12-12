package exnologialogismikou.tei.com.bestoffer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class User_selection extends AppCompatActivity {

    Button simple_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        simple_user = findViewById(R.id.simple_user);
    }


    @Override
    public void onStart() {
        super.onStart();
        simple_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(User_selection.this,Home_screen.class));
            }
        });
    }


}
