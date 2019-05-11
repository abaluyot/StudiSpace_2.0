package cs441.csusm.studispace;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class FindClass extends AppCompatActivity {

    public String username;
    public String class0;
    public String class1;
    public String class2;
    public String class3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_class);

        username = getIntent().getStringExtra("username");
        class0 = getIntent().getStringExtra("class0");
        class1 = getIntent().getStringExtra("class1");
        class2 = getIntent().getStringExtra("class2");
        class3 = getIntent().getStringExtra("class3");

        EditText findClass = (EditText) findViewById(R.id.find_class);
        final String classCode = findClass.getText().toString();

        Button enter = (Button) findViewById(R.id.button);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(class0, "empty")) {
                    addClass0toUser(classCode);
                } else if (Objects.equals(class1, "empty")) {
                    addClass1toUser(classCode);
                } else if (Objects.equals(class2, "empty")) {
                    addClass2toUser(classCode);
                } else if (Objects.equals(class3, "empty")) {
                    addClass3toUser(classCode);
                } else {
                    Intent goback = new Intent(FindClass.this, ClassList.class);
                    goback.putExtra("username", username);
                    goback.putExtra("class0", class0);
                    goback.putExtra("class1", class1);
                    goback.putExtra("class2", class2);
                    goback.putExtra("class3", class3);
                    startActivity(goback);
                }
            }
        });
    }

    public void addClass1toUser(final String classcode) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            final String TAG = "user info";
            DocumentReference doc = db.collection("users").document(username);
            doc.update("class1", classcode)

                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully updated!");
                            getClassInfo(classcode);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error updating document", e);
                        }
                    });
        }

    public void addClass0toUser(String classcode) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final String TAG = "user info";

                db.collection("users").document(username).update("class0", classcode);

            getClassInfo(classcode);
    }

    public void addClass2toUser(final String classcode) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final String TAG = "user info";
        DocumentReference doc = db.collection("users").document(username);
        doc.update("class2", classcode)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        getClassInfo(classcode);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });

    }
    public void addClass3toUser(String classcode) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final String TAG = "user info";
        db.collection("users").document(username).update("class3", classcode);
        getClassInfo(classcode);
        }

    public void getClassInfo(String class_code) {
        final String codeclass = class_code;
        FirebaseFirestore classdb = FirebaseFirestore.getInstance();
        final String TAG = "class info";

        DocumentReference docRef = classdb.collection("classes").document(codeclass);
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

                        Intent goToClass = new Intent(FindClass.this, classView.class);
                        goToClass.putExtra("classcode", codeclass);
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
