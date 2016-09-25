package com.example.ameerhamza.firebabseyoutubetest;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    private Button mbutton;
    private Button readFromDataBase;
    private EditText mName;
    private EditText mEmail;
    private EditText mAge;
    private TextView mEmailTextView,mNameTextView;

    private Student student;
    private String TAG="MainActivity";
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mName = (EditText) findViewById(R.id.mUploadName);
        mEmail = (EditText) findViewById(R.id.mUploadEmailId);
        mAge = (EditText) findViewById(R.id.mUploadAge);

        //DataBase raf
         final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        readFromDataBase = (Button) findViewById(R.id.m_read_button);

        // Write to database
        mbutton = (Button) findViewById(R.id.m_button);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // String outout=new Translate(sl, tl, text)


                student = new Student(mEmail.getText().toString(), Integer.parseInt(mAge.getText().toString()), mName.getText().toString());

                // Write a message to the database

                myRef.push().setValue(student);


                mName.getText().clear();
                mEmail.getText().clear();
                mAge.getText().clear();


            }
        });


// ...

        readFromDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.e("Count",""+dataSnapshot.getChildrenCount());
                        for(DataSnapshot mydata : dataSnapshot.getChildren()){
                            Student s = mydata.getValue(Student.class);

                            Log.e("MY Email",""+s.getEmailId());
                            Log.e("MY Name ",""+s.getName());
                            Log.e("MY age",""+s.getAge());

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
