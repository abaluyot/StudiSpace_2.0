package cs441.csusm.studispace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class ProfileViewWithImage extends AppCompatActivity {

	private FirebaseFirestore db = FirebaseFirestore.getInstance();
	final String TAG = "user profile";
	public String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_view_with_image);

	   final String fullname = getIntent().getStringExtra("name");
	   final String fname =getIntent().getStringExtra("firstname");
	   final String email = getIntent().getStringExtra("email");
	   final String bio = getIntent().getStringExtra("bio");
	   final String image = getIntent().getStringExtra("profilePic");
		username = getIntent().getStringExtra("username");
		final String isStudent = getIntent().getStringExtra("isStudent");
		final ImageView profImage = (ImageView) findViewById(R.id.profimage);

		Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/studispace-931bb.appspot.com/o/images%2F"+image+"?alt=media&token=dc28f423-d8db-49b0-a837-afd5c572652d").into(profImage);

		TextView name = (TextView)findViewById(R.id.nameText);
		name.setText(fullname);

		TextView viewemail = (TextView)findViewById(R.id.userEmail);
		viewemail.setText(email);

		TextView userbio = (TextView)findViewById(R.id.bio);
		userbio.setText(bio);

		/*Button goToEdit = (Button) findViewById(R.id.editProfile);

		goToEdit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent editProfileIntent = new Intent(ProfileViewWithImage.this, EditProfile.class);
				editProfileIntent.putExtra("username", username);
				editProfileIntent.putExtra("isStudent", isStudent);
				editProfileIntent.putExtra("firstname", fname);
				startActivity(editProfileIntent);
			}
		});*/


	}
}

