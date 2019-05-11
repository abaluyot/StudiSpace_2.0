package cs441.csusm.studispace;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class classView extends AppCompatActivity {

    final String TAG = "class info";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String classname;
    public String instructor;
    public String classDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view);

        final String classcode = getIntent().getStringExtra("classcode");
        final String classname = getIntent().getStringExtra("className");
        final String instructor = getIntent().getStringExtra("instructor");
        final String classDescription = getIntent().getStringExtra("classDescription");

        TextView className = (TextView) findViewById(R.id.className);
        className.setText(classname);

        TextView instructorName = (TextView) findViewById(R.id.instructorName);
        instructorName.setText(instructor);

        TextView classDesc = (TextView) findViewById(R.id.classDescription);
        classDesc.setText(classDescription);

    }
}