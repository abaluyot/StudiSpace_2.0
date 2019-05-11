package cs441.csusm.studispace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.koushikdutta.ion.builder.Builders;

import java.util.HashMap;
import java.util.Map;

public class SendMessage extends AppCompatActivity {

    public String username;
    public String friend_uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        username = getIntent().getStringExtra("username");
        //friend_uname = getIntent().getStringExtra("friend_uname");
        EditText recipient = (EditText) findViewById(R.id.recip);
        friend_uname = recipient.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference messages = db.collection(username + " messages");
        Map<String, Object> createMessage = new HashMap<>();
        createMessage.put("message", "Welcome to messages");
        messages.document(friend_uname).set(createMessage);

        Button send = (Button) findViewById(R.id.sendMess);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendtheMessage();
            }

        });

    }

    private void sendtheMessage() {
        FirebaseFirestore friendb = FirebaseFirestore.getInstance();
        CollectionReference messageSend = friendb.collection(friend_uname + " messages");
        Map<String, Object> createSendMessage = new HashMap<>();
        createSendMessage.put("message", "Welcome to messages");
        messageSend.document(username).set(createSendMessage);

        Intent goBack = new Intent(SendMessage.this, Messages.class);
        startActivity(goBack);

    }
}
