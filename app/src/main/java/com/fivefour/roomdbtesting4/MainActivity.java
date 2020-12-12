package com.fivefour.roomdbtesting4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    public EditText nameEdit, regEdit;
    Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdit = (EditText) findViewById(R.id.editText1);
        regEdit = (EditText) findViewById(R.id.editText2);

        okButton = (Button) findViewById(R.id.button);
         okButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 saveTask();

             }
         });

    }


    private void saveTask(){
        final String sName = nameEdit.getText().toString().trim();
        final int sRegno = Integer.parseInt(regEdit.getText().toString().trim());

        if (sName.isEmpty()) {
            nameEdit.setError("Task required");
            nameEdit.requestFocus();
            return;
        }


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();

                task.setName(sName);
                task.setRegno(sRegno);


                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();



        }

}