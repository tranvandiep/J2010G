package gokisoft.j2010g.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import gokisoft.j2010g.R;

public class EditorNewsActivity extends AppCompatActivity {
    EditText txtTitle, txtContent, txtThumbnail, txtCreatedAt;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_news);

        //Mapping
        txtTitle = findViewById(R.id.aen_title);
        txtContent = findViewById(R.id.aen_content);
        txtThumbnail = findViewById(R.id.aen_thumbnail);
        txtCreatedAt = findViewById(R.id.aen_created_at);
        saveBtn = findViewById(R.id.aen_save);

        //Dang ky lang nghe su kien
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Thiet lap Broad gui giu lieu
                Intent intent = new Intent();
                intent.setAction("ADD_NEWS");
                intent.putExtra("title", txtTitle.getText().toString());
                intent.putExtra("thumbnail", txtThumbnail.getText().toString());
                intent.putExtra("content", txtContent.getText().toString());
                intent.putExtra("created_at", txtCreatedAt.getText().toString());

                sendBroadcast(intent);

                //Finish
                finish();
            }
        });
    }
}
