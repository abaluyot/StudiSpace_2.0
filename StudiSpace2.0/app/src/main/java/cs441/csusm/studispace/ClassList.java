package cs441.csusm.studispace;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ClassList extends AppCompatActivity {

    final String TAG = "class info:";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String username;
    public String class0;
    public String class1;
    public String class2;
    public String class3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        username = getIntent().getStringExtra("username");
        class0 = getIntent().getStringExtra("class0");
        class1 = getIntent().getStringExtra("class1");
        class2 = getIntent().getStringExtra("class2");
        class3 = getIntent().getStringExtra("class3");

        TextView firstClass = (TextView) findViewById(R.id.class0);
        firstClass.setText(class0);

        TextView secondClass = (TextView) findViewById(R.id.class1);
        secondClass.setText(class1);

        TextView thirdClass = (TextView) findViewById(R.id.class2);
        thirdClass.setText(class2);

        TextView fourthClass = (TextView) findViewById(R.id.class3);
        fourthClass.setText(class3);

        Button addClass = (Button) findViewById(R.id.findclass);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToaddClass = new Intent(ClassList.this, FindClass.class);
                goToaddClass.putExtra("username",username);
                goToaddClass.putExtra("class0",class0);
                goToaddClass.putExtra("class1",class1);
                goToaddClass.putExtra("class2",class2);
                goToaddClass.putExtra("class3",class3);
                startActivity(goToaddClass);
            }
        });

    }

    public void class0Click(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("classes").document(class0);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "User class: " + document.getData());
                        String className = document.get("className").toString();
                        String instructor = document.get("instructor").toString();
                        String classdes = document.get("classDescription").toString();

                        Intent goToClass = new Intent(ClassList.this, classView.class);
                        goToClass.putExtra("classcode", class0);
                        goToClass.putExtra("className", className);
                        goToClass.putExtra("instructor", instructor);
                        goToClass.putExtra("classDescription", classdes);
                        startActivity(goToClass);
                    } else {
                        Log.d(TAG, "No classes");
                    }

                } else {
                    Log.d(TAG, "get failed with ", task.getException());

                }
            }});
    }

    public void class3Click(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("classes").document(class3);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "User class: " + document.getData());
                        String className = document.get("className").toString();
                        String instructor = document.get("instructor").toString();
                        String classdes = document.get("classDescription").toString();

                        Intent goToClass = new Intent(ClassList.this, classView.class);
                        goToClass.putExtra("classcode", class3);
                        goToClass.putExtra("className", className);
                        goToClass.putExtra("instructor", instructor);
                        goToClass.putExtra("classDescription", classdes);
                        startActivity(goToClass);
                    } else {
                        Log.d(TAG, "No classes");
                    }

                } else {
                    Log.d(TAG, "get failed with ", task.getException());

                }
            }});
    }

    public void class1Click(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("classes").document(class1);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "User class: " + document.getData());
                        String className = document.get("className").toString();
                        String instructor = document.get("instructor").toString();
                        String classdes = document.get("classDescription").toString();

                        Intent goToClass = new Intent(ClassList.this, classView.class);
                        goToClass.putExtra("classcode", class1);
                        goToClass.putExtra("className", className);
                        goToClass.putExtra("instructor", instructor);
                        goToClass.putExtra("classDescription", classdes);
                        startActivity(goToClass);
                    } else {
                        Log.d(TAG, "No classes");
                    }

                } else {
                    Log.d(TAG, "get failed with ", task.getException());

                }
            }});
    }

    public void class2Click(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("classes").document(class2);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "User class: " + document.getData());
                        String className = document.get("className").toString();
                        String instructor = document.get("instructor").toString();
                        String classdes = document.get("classDescription").toString();

                        Intent goToClass = new Intent(ClassList.this, classView.class);
                        goToClass.putExtra("classcode", class2);
                        goToClass.putExtra("className", className);
                        goToClass.putExtra("instructor", instructor);
                        goToClass.putExtra("classDescription", classdes);
                        startActivity(goToClass);
                    } else {
                        Log.d(TAG, "No classes");
                    }

                } else {
                    Log.d(TAG, "get failed with ", task.getException());

                }
            }});
    }
}


