package com.example.mobasher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobasher.Utils.ImgAdapter;
import com.example.mobasher.Utils.SharedPrefManager;

import java.util.ArrayList;

public class AppartmentsActivity extends AppCompatActivity {

    ImageView profile;

    ArrayList<String> ImgUrl= new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager Manager;
    ImgAdapter adapter;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appartments);

        profile = findViewById(R.id.imgProfile);

        ImgUrl.add("https://media.istockphoto.com/photos/new-modernist-townhouses-in-downtown-raleigh-nc-picture-id1319270438?b=1&k=20&m=1319270438&s=170667a&w=0&h=vlZki_l9xR3b6Lfe6pXdBgpA9QVGpYRyEin4GvqI9tk=");
        ImgUrl.add("https://www.aveliving.com/AVE/media/Property_Images/Florham%20Park/features/flor-apt-living-(2)-BFEAT.jpg?ext=.jpg");
        ImgUrl.add("https://www.contemporist.com/wp-content/uploads/2019/10/modern-apartment-living-room-hidden-lighting-111019-1249-03.jpg");
        ImgUrl.add("https://www.thepinnaclelist.com/wp-content/uploads/2020/12/001-Black-is-Back-Apartment-Interior-Design-Kiev-Ukraine-33bY-Architecture.jpegl");
        ImgUrl.add("https://www.contemporist.com/wp-content/uploads/2019/10/modern-bedroom-with-backlit-headboard-mountainscape-11019-101-08.jpg");


        this.recyclerView = findViewById(R.id.imgRecycler);
        Manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(Manager);
        adapter = new ImgAdapter(ImgUrl,AppartmentsActivity.this);
        recyclerView.setAdapter(adapter);



        findViewById(R.id.imgProfile).setOnClickListener(view -> startActivity(new Intent(AppartmentsActivity.this, ProfileActivity.class)));

        findViewById(R.id.suitApart).setOnClickListener(view -> startActivity(new Intent(AppartmentsActivity.this,MainActivity.class)));

        findViewById(R.id.fullApart).setOnClickListener(view -> startActivity(new Intent(AppartmentsActivity.this,MainActivity.class)));

        findViewById(R.id.simiFullApart).setOnClickListener(view -> startActivity(new Intent(AppartmentsActivity.this,MainActivity.class)));

        findViewById(R.id.logoutButton).setOnClickListener(this::openDialog);
    }

    public void openDialog(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("هل أنت متأكد من تسجيل خروجك");
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setPositiveButton("Ok", (arg0, arg1) -> {
            SharedPrefManager.getInstance(getApplicationContext()).logOut();
            finish();
        });

        alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}