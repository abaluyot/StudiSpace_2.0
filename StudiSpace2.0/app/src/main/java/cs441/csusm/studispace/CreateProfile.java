package cs441.csusm.studispace;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import java.util.Map;
import java.util.HashMap;
import java.lang.String;
import java.lang.NullPointerException;
import android.widget.EditText;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;


public class CreateProfile extends AppCompatActivity {

    private FirebaseFirestore db;
    public boolean isStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        db = FirebaseFirestore.getInstance();
        Button enterInfo = (Button) findViewById(R.id.enter);
        enterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
            });
        }

    public void radioClick(View view) {
        if (view.getId() == R.id.isStudent)
        {
            isStudent = true;
        } else if (view.getId() == R.id.isInstructor)
        {
            isStudent = false;
        }
    }

    public void addUser() {
        //gets the content from the editTexts entered by the user and stores them as strings

        EditText fnedit = (EditText) findViewById(R.id.fname);
        String firstName = fnedit.getText().toString();

        EditText lnedit = (EditText) findViewById(R.id.lname);
        String lastName = lnedit.getText().toString();

        EditText userNameEdit = (EditText) findViewById(R.id.userName);
        String userName = userNameEdit.getText().toString();

        EditText emedit = (EditText) findViewById(R.id.editEmail);
        String email = emedit.getText().toString();

        EditText passedit = (EditText) findViewById(R.id.pass);
        String password = passedit.getText().toString();

        EditText pvedit = (EditText) findViewById(R.id.passverify);
        String vpassword = pvedit.getText().toString();

        String class0 = "empty";
        String class1 = "empty";
        String class2 = "empty";
        String class3 = "empty";

            if(password.equals(vpassword)) {
                CollectionReference users = db.collection("users");

                Map<String, Object> user = new HashMap<>();
                user.put("firstName", firstName);
                user.put("lastName", lastName);
                user.put("email", email);
                user.put("password", password);
                user.put("student", isStudent);
                user.put("class0", class0);
                user.put("class1", class1);
                user.put("class2", class2);
                user.put("class3", class3);
                users.document(userName).set(user);

                Intent backtoLogin = new Intent(CreateProfile.this, MainActivity.class);
                startActivity(backtoLogin);
            }

            else{
                Toast.makeText(CreateProfile.this, "Passwords do not match",Toast.LENGTH_SHORT).show();
            }




    }
}
