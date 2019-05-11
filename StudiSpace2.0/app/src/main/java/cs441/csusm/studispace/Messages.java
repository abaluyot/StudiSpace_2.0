package cs441.csusm.studispace;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Messages extends AppCompatActivity {

    public String friend_uname;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        String message_label = getIntent().getStringExtra("message_recip");
        friend_uname = getIntent().getStringExtra("friend_uname");
        username = getIntent().getStringExtra("username");


        TextView nameEdit = (TextView) findViewById(R.id.message_view);
        nameEdit.setText(message_label);

        Button sendMessage = (Button) findViewById(R.id.sendMessage);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAdd = new Intent(Messages.this, SendMessage.class);
                gotoAdd.putExtra("friend_uname", friend_uname);
                gotoAdd.putExtra("username", username);
                startActivity(gotoAdd);
            }
        });
    }

    public void onClick(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(username + " messages").document(friend_uname);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    String message = document.get("message").toString();

                    Intent goToView = new Intent(Messages.this, ViewMessage.class);
                    goToView.putExtra("message", message);
                    goToView.putExtra("friend_uname", friend_uname);
                    goToView.putExtra("username", username);
                    startActivity(goToView);
                }
            }
        });
    }
}
