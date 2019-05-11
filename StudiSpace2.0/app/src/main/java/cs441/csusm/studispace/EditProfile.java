package cs441.csusm.studispace;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class EditProfile extends AppCompatActivity {

    private FirebaseFirestore profiledb;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private ImageView profileImage;
    FirebaseStorage storage;
    StorageReference storageReference;
    public String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profiledb = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        userName = getIntent().getStringExtra("username");
        final String isStudent = getIntent().getStringExtra("isStudent");
        final String fname = getIntent().getStringExtra("firstname");

        profileImage = (ImageView)findViewById(R.id.addimage);

        Button save = (Button)findViewById(R.id.saveInfo);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                String getImage = userName;
                addProfile(userName ,isStudent, fname, getImage);
            }

        });

    }
    public void addProfile(String uname, String student, String fname, String image) {

        EditText name =(EditText)findViewById(R.id.editnameText);
        String fullname = name.getText().toString();

        EditText userEmail = (EditText)findViewById(R.id.edituserEmail);
        String email = userEmail.getText().toString();

        EditText bioInfo = (EditText)findViewById(R.id.editbio);
        String bio = bioInfo.getText().toString();

        CollectionReference profiles = profiledb.collection("profiles");

        Map<String, Object> editprofile = new HashMap<>();
        editprofile.put("name", fullname);
        editprofile.put("email", email);
        editprofile.put("bio", bio);
        editprofile.put("profilePic", image);
        profiles.document(uname).set(editprofile);

        if(student.equals("true")) {
            Intent gobacktomain = new Intent(EditProfile.this, StudentMain.class);
            gobacktomain.putExtra("username", uname);
            gobacktomain.putExtra("firstname", fname);
            gobacktomain.putExtra("isStudent", student);
            startActivity(gobacktomain);
        }
        else if(student.equals("false")){
            Intent gobacktoImain = new Intent(EditProfile.this, InstructMain.class);
            gobacktoImain.putExtra("username", uname);
            gobacktoImain.putExtra("firstname", fname);
            gobacktoImain.putExtra("isStudent", student);
            startActivity(gobacktoImain);
        }
    }

    public void uploadImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


            @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                    && data != null && data.getData() != null )
            {
                filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    profileImage.setImageBitmap(bitmap);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ userName.toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(EditProfile.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(EditProfile.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
