package cs441.csusm.studispace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class AddFriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        EditText addFriendetext = (EditText)findViewById(R.id.friendUname);
        String friendName = addFriendetext.getText().toString();




    }
}
