package gokisoft.j2010g;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText txtFullname, txtEmail, txtAddress, txtPwd, txtConfirmPwd;
    Button btnRegister, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Mapping
        txtFullname = findViewById(R.id.ar_txt_fullname);
        txtEmail = findViewById(R.id.ar_txt_email);
        txtAddress = findViewById(R.id.ar_txt_address);
        txtPwd = findViewById(R.id.ar_txt_password);
        txtConfirmPwd = findViewById(R.id.ar_txt_confirm_pwd);

        btnRegister = findViewById(R.id.ar_btn_register);
        btnBack = findViewById(R.id.ar_btn_back);

        //Dang ky lang nghe su kien
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    private void back() {
        finish();
    }

    private void register() {
        String fullname = txtFullname.getText().toString();
        String email = txtEmail.getText().toString();
        String address = txtAddress.getText().toString();
        String pwd = txtPwd.getText().toString();
        String confirmPwd = txtConfirmPwd.getText().toString();

        if(!pwd.equals(confirmPwd)) {
            Toast.makeText(this, "Mat khau khong khop!!!", Toast.LENGTH_SHORT).show();
        } else {
            //Dang ky thang cong
            Intent i = new Intent();
            i.putExtra("fullname", fullname);
            i.putExtra("email", email);
            i.putExtra("address", address);
            i.putExtra("password", pwd);
            setResult(LoginActivity.CODE_REGISTER, i);

            //Save du lieu => SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("J2010G", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //Luu
            editor.putString("fullname", fullname);
            editor.putString("email", email);
            editor.putString("address", address);
            editor.putString("password", pwd);

            editor.commit();//Luu thay doi trong SharedPrerences.

            finish();
        }
    }
}
