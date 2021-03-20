package gokisoft.j2010g;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Mapping object trong XML sang Java
    EditText edEmail, edPassword;
    Button btnSave, btnReset, btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapping object java & component trong xml
        edEmail = findViewById(R.id.am_ed_email);
        edPassword = findViewById(R.id.am_ed_password);
        btnSave = findViewById(R.id.am_btn_save);
        btnReset = findViewById(R.id.am_btn_reset);
        btnStart = findViewById(R.id.am_btn_start);

        //Bat su kien khi nguoi dung click vao button save & reset
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noi xu ly code -> khi click vao button save
                String email = edEmail.getText().toString();
                String pwd = edPassword.getText().toString();

                Log.d("MainActivity", email);
                Log.d(MainActivity.class.getName(), pwd);

                //Gia su neu email: aptech@com & pwd: 123 => show message: Login success!
                //Show message: Login failed.
                if(email.equals("aptech@com") && pwd.equals("123")) {
                    //login success
                    Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                } else {
                    //login failed
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noi xu ly code -> khi click vao button reset
                edEmail.setText("");
                edPassword.setText("");
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, TestActivity.class);
                startActivity(in);
            }
        });
    }
}
