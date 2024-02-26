package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.net.InetSocketAddress;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private EditText txtid,txtName,txtPhone_Number;
    private CheckBox status;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Init();
        Intent intent = getIntent();
        ArrayList<Integer> arr = intent.getIntegerArrayListExtra("listId");
        btnAdd.setOnClickListener(v ->{
            int id = 0;
            try{
                id = Integer.parseInt(txtid.getText().toString());
            }catch (Exception ex){
                Toast.makeText(this, "Cần điền id", Toast.LENGTH_SHORT).show();
            }
            String name = txtName.getText().toString();
            String phone = txtPhone_Number.getText().toString();
            boolean stat = status.isChecked();
            if(Validate(id,name,phone,arr)){
                Intent i = new Intent();
                Bundle b =new Bundle();
                b.putInt("id",id);
                b.putString("name",name);
                b.putString("phone",phone);
                b.putBoolean("status",stat);

                i.putExtras(b);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }
    private boolean Validate(int id, String name, String phone, ArrayList<Integer> listId) {
        if(name.equals("") || phone.equals("")){
            Toast.makeText(this, "Cần điền đầy đủ", Toast.LENGTH_SHORT).show();
            return false;
        }
        for(int i=0;i<listId.size();i++){
            if(listId.get(i) == id) {
                Toast.makeText(this, "id đã tồn tại", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    public void Init(){
        txtid = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtPhone_Number = findViewById(R.id.txtPhoneNumber);
        btnAdd = findViewById(R.id.btnAdd);
        status = findViewById(R.id.status);

    }
}