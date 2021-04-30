package com.example.gradesapp1801682004;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class crud extends AppCompatActivity {

    DBHelper myDb;
    EditText editName,editSurname,editGrade,editID;
    Button btnAdd;
    Button btnView;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        myDb = new DBHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editSurname = (EditText) findViewById(R.id.editText_surname);
        editGrade = (EditText) findViewById(R.id.editText_grade);
        editID = (EditText) findViewById(R.id.editText_id);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }

    public void AddData() {
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       boolean isInserted = myDb.insertData(editName.getText().toString(), editSurname.getText().toString(), editGrade.getText().toString());
                       if(isInserted==true)
                           Toast.makeText(crud.this, "Information inserted", Toast.LENGTH_SHORT).show();
                       else
                           Toast.makeText(crud.this, "Information was NOT inserted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Cursor result = myDb.getAllData();
                        if(result.getCount() == 0) {
                            showMessage("Error", "No data");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()) {
                            buffer.append("ID: " + result.getString(0) +"\n");
                            buffer.append("Name: " + result.getString(1) +"\n");
                            buffer.append("Surname: " + result.getString(2) +"\n");
                            buffer.append("Grade: " + result.getString(3) +"\n\n");
                        }

                        showMessage("Grades", buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(editID.getText().toString(), editName.getText().toString(), editSurname.getText().toString(), editGrade.getText().toString());
                        if(isUpdated == true )
                            Toast.makeText(crud.this, "Information updated", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(crud.this, "Information was NOT updated", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Integer deletedRows = myDb.deleteData(editID.getText().toString());
                            if(deletedRows > 0 )
                                Toast.makeText(crud.this, "Information deleted", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(crud.this, "Information was NOT deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

}