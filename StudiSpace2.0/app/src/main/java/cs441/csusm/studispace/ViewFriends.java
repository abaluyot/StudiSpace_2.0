package cs441.csusm.studispace;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.koushikdutta.ion.builder.Builders;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ViewFriends extends AppCompatActivity {

    public String friend1name;
    public String friend2name;
    public String friend3name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friends);

        friend1name = getIntent().getStringExtra("friend1name");
        friend2name = getIntent().getStringExtra("friend2name");
        friend3name = getIntent().getStringExtra("friend3name");


        final ImageView friend1 = (ImageView)findViewById(R.id.friend1pic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/studispace-931bb.appspot.com/o/images%2F"+friend1name+"?alt=media&token=dc28f423-d8db-49b0-a837-afd5c572652d")
                .resize(150,150).into(friend1);

        final ImageView friend2 = (ImageView)findViewById(R.id.friend2pic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/studispace-931bb.appspot.com/o/images%2F"+friend2name+"?alt=media&token=dc28f423-d8db-49b0-a837-afd5c572652d")
                .resize(150,150).into(friend2);

        final ImageView friend3 = (ImageView)findViewById(R.id.friend3pic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/studispace-931bb.appspot.com/o/images%2F"+friend3name+"?alt=media&token=dc28f423-d8db-49b0-a837-afd5c572652d")
                .resize(150,150).into(friend3);



    }


    public void friend1profile(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("profiles").document(friend1name);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String name = document.get("name").toString();
                        String email = document.get("email").toString();
                        String bio = document.get("bio").toString();
                        String image =document.get("profilePic").toString();


                        Intent goToProfile = new Intent(ViewFriends.this, ProfileViewWithImage.class);
                        goToProfile.putExtra("username", friend1name);
                        goToProfile.putExtra("name", name);
                        goToProfile.putExtra("email", email);
                        goToProfile.putExtra("bio", bio);
                        goToProfile.putExtra("profilePic",image);
                        startActivity(goToProfile);

                    } else {

                        Toast.makeText(ViewFriends.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }
        });
    }

    public void friend2profile(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("profiles").document(friend2name);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String name = document.get("name").toString();
                        String email = document.get("email").toString();
                        String bio = document.get("bio").toString();
                        String image =document.get("profilePic").toString();


                        Intent goToProfile = new Intent(ViewFriends.this, ProfileViewWithImage.class);
                        goToProfile.putExtra("username", friend2name);
                        goToProfile.putExtra("name", name);
                        goToProfile.putExtra("email", email);
                        goToProfile.putExtra("bio", bio);
                        goToProfile.putExtra("profilePic",image);
                        startActivity(goToProfile);

                    } else {

                        Toast.makeText(ViewFriends.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }
        });
    }

    public void friend3profile(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("profiles").document(friend3name);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String name = document.get("name").toString();
                        String email = document.get("email").toString();
                        String bio = document.get("bio").toString();
                        String image =document.get("profilePic").toString();


                        Intent goToProfile = new Intent(ViewFriends.this, ProfileViewWithImage.class);
                        goToProfile.putExtra("username", friend3name);
                        goToProfile.putExtra("name", name);
                        goToProfile.putExtra("email", email);
                        goToProfile.putExtra("bio", bio);
                        goToProfile.putExtra("profilePic",image);
                        startActivity(goToProfile);

                    } else {

                        Toast.makeText(ViewFriends.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }
        });
    }
}
