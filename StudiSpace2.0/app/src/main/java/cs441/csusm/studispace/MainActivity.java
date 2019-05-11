package cs441.csusm.studispace;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.String;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        final String TAG = "UserInfo";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button createacc = findViewById(R.id.createacct);
        Button login = findViewById(R.id.login);
        db = FirebaseFirestore.getInstance();

        //when create account button is clicked
        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createaccountIntent = new Intent(MainActivity.this, CreateProfile.class);
                startActivity(createaccountIntent);
            }
        });

        //login with email and password
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText unedit = (EditText) findViewById(R.id.loginuser);
                 final String userName = unedit.getText().toString();
                EditText passedit = (EditText) findViewById(R.id.loginpassword);
                 final String password = passedit.getText().toString();
                            //need to check the password against password in the database
                DocumentReference docRef = db.collection("users").document(userName);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "User data: " + document.getData());
                                String passWord = document.get("password").toString();
                                String isStudent = document.get("student").toString();
                                String firstName = document.get("firstName").toString();
                                String lastName = document.get("lastName").toString();
                                String email = document.get("email").toString();

                                if(passWord.equals(password)) {

                                    if (isStudent == "true") {
                                        Intent gotoStudentMain = new Intent(MainActivity.this, StudentMain.class);
                                        gotoStudentMain.putExtra("firstname", firstName);
                                        gotoStudentMain.putExtra("lastname", lastName);
                                        gotoStudentMain.putExtra("email", email);
                                        gotoStudentMain.putExtra("username", userName);
                                        gotoStudentMain.putExtra("isStudent", isStudent);
                                        startActivity(gotoStudentMain);
                                    } else if (isStudent == "false") {
                                        Intent gotoInstructMain = new Intent(MainActivity.this, InstructMain.class);
                                        gotoInstructMain.putExtra("firstname", firstName);
                                        gotoInstructMain.putExtra("lastname", lastName);
                                        gotoInstructMain.putExtra("email", email);
                                        gotoInstructMain.putExtra("username", userName);
                                        gotoInstructMain.putExtra("isStudent", isStudent);
                                        startActivity(gotoInstructMain);
                                    }
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Wrong password",Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.d(TAG, "No such user");
                                Toast.makeText(MainActivity.this, "User does not exist",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });


            }
        });
    }
}
