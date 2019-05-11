package cs441.csusm.studispace;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class InstructMain extends AppCompatActivity {

    final String TAG = "userprofile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruct_main);

        final String fname = getIntent().getStringExtra("firstname");
        final String lname = getIntent().getStringExtra("lastname");
        final String email = getIntent().getStringExtra("email");
        final String username = getIntent().getStringExtra("username");
        final String isStudent = getIntent().getStringExtra("isStudent");

        TextView title = (TextView)findViewById(R.id.ihome);
        title.setText(fname +"'s Homepage");

        Button profile = findViewById(R.id.iprofile);
        Button classes = findViewById(R.id.iclasses);
        Button friends = findViewById(R.id.iFriends);
        Button messages = findViewById(R.id.imessages);
        Button addclass = findViewById(R.id.add);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("profiles").document(username);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "User data: " + document.getData());
                                String name = document.get("name").toString();
                                String email = document.get("email").toString();
                                String bio = document.get("bio").toString();
                                String image = document.get("profilePic").toString();


                                Intent goToProfile = new Intent(InstructMain.this, ProfileViewWithImage.class);
                                goToProfile.putExtra("username", username);
                                goToProfile.putExtra("firstname", fname);
                                goToProfile.putExtra("name", name);
                                goToProfile.putExtra("email", email);
                                goToProfile.putExtra("bio", bio);
                                goToProfile.putExtra("profilePic",image);
                                goToProfile.putExtra("isStudent", isStudent);
                                startActivity(goToProfile);

                            } else {
                                Log.d(TAG, "No such profile");
                                Toast.makeText(InstructMain.this, "User does not exist", Toast.LENGTH_SHORT).show();
                                Intent makeProf = new Intent(InstructMain.this, EditProfile.class);
                                makeProf.putExtra("username", username);
                                makeProf.putExtra("firstname", fname);
                                makeProf.putExtra("isStudent", isStudent);
                                startActivity(makeProf);
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }

        });

        classes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("users").document(username);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()) {
                                Log.d(TAG, "User class: " + document.getData());
                                String class0 = document.get("class0").toString();
                                String class1 = document.get("class1").toString();
                                String class2 = document.get("class2").toString();
                                String class3 = document.get("class3").toString();

                                Intent goToClass = new Intent(InstructMain.this, ClassList.class);
                                goToClass.putExtra("class0", class0);
                                goToClass.putExtra("class1", class1);
                                goToClass.putExtra("class2", class2);
                                goToClass.putExtra("class3", class3);
                                goToClass.putExtra("isStudent", isStudent);
                                startActivity(goToClass);
                            }
                            else{
                                Log.d(TAG, "No classes");
                            }
                        }else{
                            Log.d(TAG, "get failed with ", task.getException());

                        }
                    }

                });

            }
        });

        friends.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("friends").document(username);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "User friends: " + document.getData());
                                String friend1 = document.get("friend1").toString();
                                String friend2 = document.get("friend2").toString();
                                String friend3 = document.get("friend3").toString();

                                Intent goToFriends = new Intent(InstructMain.this, ViewFriends.class);
                                goToFriends.putExtra("username", username);
                                goToFriends.putExtra("friend1name", friend1);
                                goToFriends.putExtra("friend2name", friend2);
                                goToFriends.putExtra("friend3name", friend3);
                                startActivity(goToFriends);

                            } else {
                                Log.d(TAG, "No such profile");
                                Toast.makeText(InstructMain.this, "User does not exist", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "get failed with ", task.getException());
                                FirebaseFirestore datab = FirebaseFirestore.getInstance();
                                CollectionReference profiles = datab.collection("friends");

                                Map<String, Object> addfriend = new HashMap<>();
                                addfriend.put("friend1", "hpotter");
                                profiles.document(username).set(addfriend);

                                Intent viewFriendsIntent = new Intent(InstructMain.this, ViewFriends.class);
                                viewFriendsIntent.putExtra("username", username);
                                viewFriendsIntent.putExtra("friend1", "hpotter");
                                startActivity(viewFriendsIntent);

                            }
                        }else {
                            Log.d(TAG, "get failed with ", task.getException());

                        }
                    }
                });

            }
        });

        messages.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                /*final Intent gotoMessages = new Intent(InstructMain.this, Messages.class);
                db.collection(username+" messages")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        String friendname = document.getId();
                                        String message = document.getData().toString();
                                        gotoMessages.putExtra("friend_uname", friendname);
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
                gotoMessages.putExtra("username", username);
                startActivity(gotoMessages);
*/
                DocumentReference docRef = db.collection(username + " messages").document();
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "User messages: " + document.getData());
                                String message1 = document.get("message1").toString();
                                String message2 = document.get("message2").toString();
                                String message3 = document.get("message3").toString();
                                String message4 = document.get("message4").toString();

                                //how to access all documents in a collection would be SOOOO useful for messages, friends, classes
                                Intent gotoMessages = new Intent(InstructMain.this, Messages.class);
                                gotoMessages.putExtra("message1", message1);
                                gotoMessages.putExtra("message2", message2);
                                gotoMessages.putExtra("message3", message3);
                                gotoMessages.putExtra("message4", message4);
                                startActivity(gotoMessages);

                            } else {
                                Log.d(TAG, "No such profile");
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                CollectionReference messages = db.collection(username + " messages");
                                Map<String, Object> createMessage = new HashMap<>();
                                createMessage.put("message", "Welcome to messages");
                                messages.document("hpotter").set(createMessage);
                                Intent gotoMessages = new Intent(InstructMain.this, Messages.class);
                                gotoMessages.putExtra("message", "Welcome to messages");
                                gotoMessages.putExtra("message_recip", "Harry Potter");
                                gotoMessages.putExtra("friend_uname", "hpotter");
                                gotoMessages.putExtra("username", username);
                                startActivity(gotoMessages);

                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
        });



        addclass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent addClassIntent = new Intent (InstructMain.this, AddClass.class);
                addClassIntent.putExtra("firstname", fname);
                addClassIntent.putExtra("lastname",lname);
                addClassIntent.putExtra("email",email);
                addClassIntent.putExtra("username", username);
                startActivity(addClassIntent);
            }
        });

    }
}
