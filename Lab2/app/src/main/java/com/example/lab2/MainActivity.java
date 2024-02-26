package com.example.lab2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;


import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.net.Inet4Address;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btnThem;
    private Button btnXoa;
    private EditText name;
    private Adapter adapter;
    private ArrayList<Contact> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        name = findViewById(R.id.txtTen);

        items = new ArrayList<Contact>();
        items.add(new Contact(1, "quan", "0890909", false));
        items.add(new Contact(2, "linh", "232423", false));
        items.add(new Contact(3, "be mo", "4234234", false));
        items.add(new Contact(4, "thanh", "42342", false));

        adapter = new Adapter(items, this);
        listView.setAdapter(adapter);

        btnThem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            ArrayList<Integer> arr = new ArrayList<>();
            for (Contact c : items) {
                arr.add(c.getId());
                intent.putIntegerArrayListExtra("listId", arr);
                startActivityForResult(intent, 100);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("ban co muon xoa khong");

                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < items.size(); ) {
                            if (items.get(i).getStatus() == true) {
                                items.remove(i);
                            } else {
                                i++;
                            }
                        }
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle b = data.getExtras();

            int id = b.getInt("id");
            String name = b.getString("name");
            String phone = b.getString("phone");
            Boolean status = b.getBoolean("status");

            Contact contact = new Contact(id, name, phone, status);
            items.add(contact);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}