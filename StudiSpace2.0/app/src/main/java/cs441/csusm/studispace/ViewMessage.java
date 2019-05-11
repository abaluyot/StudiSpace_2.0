package cs441.csusm.studispace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);

        final String username = getIntent().getStringExtra("username");
        final String friend_uname = getIntent().getStringExtra("friend_uname");
        final String message = getIntent().getStringExtra("message");

        TextView recipient = (TextView)findViewById(R.id.messageRecip);
        recipient.setText(friend_uname);

        TextView messageContent = (TextView)findViewById(R.id.messageContent);
        messageContent.setText(message);

        Button reply = (Button)findViewById(R.id.reply);
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSend = new Intent(ViewMessage.this, SendMessage.class);
                goToSend.putExtra("username", username);
                //goToSend.putExtra("friend_uname",friend_uname);
                startActivity(goToSend);
            }
        });


    }
}
