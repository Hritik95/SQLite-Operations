package com.example.sqlite_operation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,contact,email;
    Button add_btn,update_btn,delete_btn,show_btn;
    DBconnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);

        add_btn = findViewById(R.id.add_btn);
        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);
        show_btn = findViewById(R.id.show_btn);

        db = new DBconnection(this);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametext = name.getText().toString();
                String contacttext = contact.getText().toString();
                String emailtext = email.getText().toString();

                Boolean checkinsert = db.adduserdata(nametext,contacttext,emailtext);

                if (checkinsert == true)
                    Toast.makeText(MainActivity.this, "New Entry Added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Failed to Insert", Toast.LENGTH_SHORT).show();
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametext = name.getText().toString();
                String contacttext = contact.getText().toString();
                String emailtext = email.getText().toString();

                Boolean checkupdate = db.updateuserdata(nametext,contacttext,emailtext);

                if (checkupdate == true)
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show();
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametext = name.getText().toString();

                Boolean checkdelete = db.deleteuserdata(nametext);

                if (checkdelete == true)
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Failed to Delete", Toast.LENGTH_SHORT).show();
            }
        });

        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.show();
                if (res.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext()){
                    buffer.append("Name : " + res.getString(0) + "\n");
                    buffer.append("Contact : " + res.getString(1) + "\n");
                    buffer.append("Email Address : " + res.getString(2) + "\n\n");

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User Data");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });
    }
}