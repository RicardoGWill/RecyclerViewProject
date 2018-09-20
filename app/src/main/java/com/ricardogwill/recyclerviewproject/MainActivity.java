package com.ricardogwill.recyclerviewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

// Help from the following series: https://www.youtube.com/watch?v=Nw9JF55LDzE&list=PLrnPJCHvNZuBtTYUuc5Pyo4V7xZ2HNtf4
// Note that a Maven repository was put in the Project build.gradle file,
// and two dependencies "recyclerview" and "cardview" were put in the Module build.gradle file.

public class MainActivity extends AppCompatActivity {

    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button insertButton;
    private EditText insertEditText;
    private Button removeButton;
    private EditText removeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();
        setButtons();

    }

    public void insertItem(int position) {
        mExampleList.add(position, new ExampleItem(R.drawable.ic_android, "New Item At Position " + (position+1), "This is Line 2."));
        adapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void changeItem(int position, String text) {
        mExampleList.get(position).changeText1(text);
        adapter.notifyItemChanged(position);
    }

    public void createExampleList() {

        // Note that the ExampleItem.java file is the name (minus ".java") that you put between < and >.
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 3", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 5", "Line 6"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 7", "Line 8"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 9", "Line 10"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 11", "Line 12"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 13", "Line 14"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 15", "Line 16"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 17", "Line 18"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 19", "Line 20"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 21", "Line 22"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 23", "Line 24"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 25", "Line 26"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 27", "Line 28"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 29", "Line 30"));

    }

    public void buildRecyclerView() {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new ExampleAdapter(mExampleList);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void setButtons() {

        insertButton = findViewById(R.id.insert_button);
        insertEditText = findViewById(R.id.insert_editText);
        removeButton = findViewById(R.id.remove_button);
        removeEditText = findViewById(R.id.remove_editText);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position  = Integer.parseInt(insertEditText.getText().toString());
                if (position > 0) {
                    insertItem(position - 1); // The "insertItem()" method is defined below.
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a positive number.", Toast.LENGTH_SHORT).show();
                }
                insertEditText.setText("");
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position  = Integer.parseInt(removeEditText.getText().toString());
                if (position > 0) {
                    removeItem(position - 1); // The "removeItem()" method is defined below.
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a positive number.", Toast.LENGTH_SHORT).show();
                }
                removeEditText.setText("");
            }
        });

    }

}
