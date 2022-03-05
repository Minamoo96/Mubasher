package com.example.mobasher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.mobasher.Utils.ImgAdapter;

import java.util.ArrayList;

public class AppartmentsActivity extends AppCompatActivity {

    ArrayList<String> ImgUrl= new ArrayList<String>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager Manager;
    ImgAdapter adapter;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appartments);

        ImgUrl.add("https://media.istockphoto.com/photos/new-modernist-townhouses-in-downtown-raleigh-nc-picture-id1319270438?b=1&k=20&m=1319270438&s=170667a&w=0&h=vlZki_l9xR3b6Lfe6pXdBgpA9QVGpYRyEin4GvqI9tk=");
        ImgUrl.add("https://www.aveliving.com/AVE/media/Property_Images/Florham%20Park/features/flor-apt-living-(2)-BFEAT.jpg?ext=.jpg");
        ImgUrl.add("https://www.contemporist.com/wp-content/uploads/2019/10/modern-apartment-living-room-hidden-lighting-111019-1249-03.jpg");
        ImgUrl.add("https://www.thepinnaclelist.com/wp-content/uploads/2020/12/001-Black-is-Back-Apartment-Interior-Design-Kiev-Ukraine-33bY-Architecture.jpegl");
        ImgUrl.add("https://www.contemporist.com/wp-content/uploads/2019/10/modern-bedroom-with-backlit-headboard-mountainscape-11019-101-08.jpg");
//         ImgUrl.add("https://cdn-s3.si.com/s3fs-public/si/dam/assets/13/02/13/130213172915-michael-jordan-05717484-single-image-cut.jpg");
//        ImgUrl.add("http://cdn.playbuzz.com/cdn/5cb29908-40a5-42d4-831d-5bea595bcf05/5246cb13-4c32-45ba-89ad-c63cbbcdfde3.jpg");
//        ImgUrl.add("http://i.dailymail.co.uk/i/pix/2015/09/24/17/2CB89DDF00000578-0-image-a-1_1443111464150.jpg");
//        ImgUrl.add("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg");


        this.recyclerView = findViewById(R.id.imgRecycler);
        Manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(Manager);
        adapter = new ImgAdapter(ImgUrl,AppartmentsActivity.this);
        recyclerView.setAdapter(adapter);


        findViewById(R.id.suitApart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppartmentsActivity.this,MainActivity.class));
            }
        });

        findViewById(R.id.fullApart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppartmentsActivity.this,MainActivity.class));
            }
        });

        findViewById(R.id.simiFullApart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppartmentsActivity.this,MainActivity.class));
            }
        });

        findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(v);
            }
        });
    }

    public void openDialog(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("هل أنت متأكد من تسجيل خروجك");
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                startActivity(new Intent(AppartmentsActivity.this, LoginActivity.class));
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}