package cs441.csusm.studispace;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddClass extends AppCompatActivity {

    final String TAG = "class info";
    private FirebaseFirestore classdb = FirebaseFirestore.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        final String fname = getIntent().getStringExtra("firstname");
        final String lname = getIntent().getStringExtra("lastname");
        final String email = getIntent().getStringExtra("email");
        final String uname = getIntent().getStringExtra("username");
        final String fullname = fname + " " + lname;

        final Button addClass = (Button) findViewById(R.id.addClass);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass(fullname);
            }

        });
    }
        public void addClass(String name) {
            EditText classNameEdit = (EditText) findViewById(R.id.classname);
            String className = classNameEdit.getText().toString();

            EditText classCodeEdit = (EditText) findViewById(R.id.classCode);
            String classcode = classCodeEdit.getText().toString();

            EditText classDescEdit = (EditText) findViewById(R.id.classDescrip);
            String classDescription = classDescEdit.getText().toString();

            CollectionReference classes = classdb.collection("classes");

            Map<String, Object> newclass = new HashMap<>();
            newclass.put("instructor", name);
            newclass.put("className", className);
            newclass.put("classDescription", classDescription);
            classes.document(classcode).set(newclass);

            Intent goToInstructClass = new Intent(AddClass.this, ClassList.class);
            goToInstructClass.putExtra("classcode", classcode);
            startActivity(goToInstructClass);
    }

    }
