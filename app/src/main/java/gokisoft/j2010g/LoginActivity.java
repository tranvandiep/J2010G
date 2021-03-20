package gokisoft.j2010g;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gokisoft.j2010g.model.User;

public class LoginActivity extends AppCompatActivity {
    public static int CODE_REGISTER = 101;

    EditText txtEmail, txtPwd;
    Button btnSave, btnRegister, btnShow, btnLoadData;
    TextView viewNotify;

    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Mapping item trong layout -> code java
        txtEmail = findViewById(R.id.al_txt_email);
        txtPwd = findViewById(R.id.al_txt_password);
        btnSave = findViewById(R.id.al_btn_login);
        btnRegister = findViewById(R.id.al_btn_register);
        btnShow = findViewById(R.id.al_btn_show);
        btnLoadData = findViewById(R.id.al_btn_load_data);
        viewNotify = findViewById(R.id.al_view_notify);

        //Dang ky lang nghe su kien khi click btn login & register
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterActivity();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserInfor();
            }
        });

        btnLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("J2010G", MODE_PRIVATE);
                //Doc noi dung du lieu
                String fullname = sharedPreferences.getString("fullname", "");
                String email = sharedPreferences.getString("email", "");
                String address = sharedPreferences.getString("address", "");
                String password = sharedPreferences.getString("password", "");

                user = new User(fullname, email, address, password);

                btnLoadData.setVisibility(View.GONE);
                viewNotify.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showUserInfor() {
        if(user == null) {
            Toast.makeText(this, "Chua co tai khoan nao duoc dang ky!", Toast.LENGTH_SHORT).show();
            return;
        }

//        Intent i = new Intent(this, UserInforActivity.class);
//        i.putExtra("fullname", user.getFullname());
//        i.putExtra("email", user.getEmail());
//        i.putExtra("address", user.getAddress());
//
//        startActivity(i);
        //Dialog default
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Thong Tin Nguoi Dung")
//                .setMessage(user.toString())
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .show();
        //Custom
        View v = LayoutInflater.from(this).inflate(R.layout.user_infor_view, null);
        TextView txtDialogFullname = v.findViewById(R.id.uiv_fullname);
        TextView txtDialogAddress = v.findViewById(R.id.uiv_address);
        TextView txtDialogEmail = v.findViewById(R.id.uiv_email);

        Button btnClose = v.findViewById(R.id.uiv_btn_close);

        txtDialogFullname.setText(user.getFullname());
        txtDialogAddress.setText(user.getAddress());
        txtDialogEmail.setText(user.getEmail());

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        final AlertDialog dialog = builder.show();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void showRegisterActivity() {
        Intent i = new Intent(this, RegisterActivity.class);

//        startActivity(i);
        startActivityForResult(i, CODE_REGISTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == CODE_REGISTER) {
            String fullname = data.getStringExtra("fullname");
            String email = data.getStringExtra("email");
            String address = data.getStringExtra("address");
            String pwd = data.getStringExtra("password");

            user = new User(fullname, email, address, pwd);

            Toast.makeText(this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkLogin() {
        if(user == null) {
            Toast.makeText(this, "Chua co tai khoan nao duoc dang ky!", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = txtEmail.getText().toString();
        String pwd = txtPwd.getText().toString();

        if(email.equalsIgnoreCase(user.getEmail()) && pwd.equals(user.getPwd())) {
            //Login success
            Toast.makeText(this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
        } else {
            //Login failed
            Toast.makeText(this, "Kiem tra email hoac password", Toast.LENGTH_SHORT).show();
        }
    }
}
