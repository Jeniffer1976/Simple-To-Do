package sg.edu.rp.c346.id21025290.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvToDo;
    Spinner spToDo;
    EditText etToDo;
    Button btnAdd, btnClear, btnDelete;
    ArrayList toDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvToDo = findViewById(R.id.listViewToDo);
        spToDo = findViewById(R.id.spinnerTask);
        etToDo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);

        toDoList = new ArrayList<String>();

        ArrayAdapter aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toDoList);
        lvToDo.setAdapter(aaToDo);


        spToDo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        etToDo.setHint(getString(R.string.hintAdd));
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etToDo.setHint(getString(R.string.hintRemove));
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDoList.add(etToDo.getText().toString());
                aaToDo.notifyDataSetChanged();
                etToDo.setText("");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toDoList.isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.errorTask), Toast.LENGTH_SHORT).show();
                }
                else {
                    int pos = Integer.parseInt(etToDo.getText().toString());
                    if (pos >= toDoList.size() && pos < 0) {
                        Toast.makeText(MainActivity.this, getString(R.string.errorIndex), Toast.LENGTH_SHORT).show();
                    } else {
                        toDoList.remove(pos);
                        aaToDo.notifyDataSetChanged();
                        etToDo.setText("");
                    }

                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDoList.clear();
                aaToDo.notifyDataSetChanged();
            }
        });


    }
}