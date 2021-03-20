package gokisoft.j2010g;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserInforActivity extends AppCompatActivity {
    TextView txtFullname, txtEmail, txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infor);

        //Mapping
        txtFullname = findViewById(R.id.aui_txt_fullname);
        txtEmail = findViewById(R.id.aui_txt_email);
        txtAddress = findViewById(R.id.aui_txt_address);

        //Doc du lieu gui tu LoginActivity
        String fullname = getIntent().getStringExtra("fullname");
        String email = getIntent().getStringExtra("email");
        String address = getIntent().getStringExtra("address");

        txtFullname.setText(fullname);
        txtEmail.setText(email);
        txtAddress.setText(address);
    }
}
