package gokisoft.j2010g.animal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gokisoft.j2010g.R;

public class AnimalActivity extends AppCompatActivity {
    List<String> dataList = new ArrayList<>();

    ListView listView;

    ArrayAdapter<String> adapter;

    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        listView = findViewById(R.id.aa_listview);

        //Fake data
        dataList.add("Dog");
        dataList.add("Cat");
        dataList.add("Tiger");
        dataList.add("Beer");
        dataList.add("Bee");
        dataList.add("Elephant");

        //Thiet lap adapter
        adapter = new ArrayAdapter<String>(this, R.layout.item_animal, R.id.ia_animal_name, dataList);

        //Cai dat adapter vao Listview
        listView.setAdapter(adapter);

        //Bat su kien khi nguoi dung click vao item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String itemName = dataList.get(position);

                Toast.makeText(AnimalActivity.this, itemName, Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_animal_app, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_animal, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.maa_new_animal:
                showDialogAddAnimal();
                break;
            case R.id.maa_exit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        switch (id) {
            case R.id.mca_edit_animal:
                currentIndex = index;
                showDialogAddAnimal();
                break;
            case R.id.mca_delete_animal:
                dataList.remove(index);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    void showDialogAddAnimal() {
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_animal, null);

        final EditText txtAnimalName = v.findViewById(R.id.da_animal_name);

        if(currentIndex >= 0) {
            txtAnimalName.setText(dataList.get(currentIndex));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String animalName = txtAnimalName.getText().toString();

                if(currentIndex >= 0) {
                    dataList.set(currentIndex, animalName);
                    currentIndex = -1;
                } else {
                    dataList.add(animalName);
                }

                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currentIndex = -1;
            }
        }).show();
    }
}
