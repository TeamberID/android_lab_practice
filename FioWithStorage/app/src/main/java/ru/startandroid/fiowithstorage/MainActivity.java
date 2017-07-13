package ru.startandroid.fiowithstorage;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String SAVED_TEXT = "saved_text";
    private static final String SAVED_FAM = "saved_fam";
    private static final String SAVED_OTC = "saved_otc";
    private static final String SAVED_TIME = "saved_time";
    private static final String PATH = "path";

    SharedPreferences sPref;
    EditText name;
    EditText fam;
    EditText otc;
    Button save;
    Button load;
    ImageView photo;
    Button takePhoto;
    Bitmap photoImage;

    public static final int TAG = 3;
    public static final int CAMERA_REQUEST = 1;
    public static final int CAMERA_PERMISSION_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.et_name);
        fam = (EditText) findViewById(R.id.et_fam);
        otc = (EditText) findViewById(R.id.et_otc);
        save = (Button) findViewById(R.id.save_btn);
        load = (Button) findViewById(R.id.load_btn);


        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadText();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveText();
            }
        });

        photo = (ImageView) findViewById(R.id.photo);
        takePhoto = (Button) findViewById(R.id.cam);
        final String[] permissions = {Manifest.permission.CAMERA};

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)  //Проверяем наличие разрешения в манифесте
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale //Проверяем, иожно ли показывать реквест
                            (MainActivity.this, Manifest.permission.CAMERA)) {
                        Toast.makeText(getApplicationContext(), "Turn on camera permission!", Toast.LENGTH_SHORT).show(); //Если нет, то просим юзера вручную поставить разрешение
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, permissions, CAMERA_PERMISSION_CODE); //Если да, то запрашиваем разрешение
                    }
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST);
                }
            }

        });

    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path) {

        try {
            File f = new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img = (ImageView) findViewById(R.id.photo);
            img.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        long last = sPref.getLong(SAVED_TIME, 0);
        if (System.currentTimeMillis() - last < 60000) {
            String savedText = sPref.getString(SAVED_TEXT, "");
            name.setText(savedText);
            savedText = sPref.getString(SAVED_FAM, "");
            fam.setText(savedText);
            savedText = sPref.getString(SAVED_OTC, "");
            otc.setText(savedText);
            loadImageFromStorage(sPref.getString(PATH, ""));
            Toast.makeText(this, "LOADED" + (System.currentTimeMillis() - last), Toast.LENGTH_SHORT).show();
        }
    }

    void saveText() {
        long time = System.currentTimeMillis();
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putLong(SAVED_TIME, time);
        ed.putString(SAVED_TEXT, name.getText().toString());
        ed.putString(SAVED_FAM, fam.getText().toString());
        ed.putString(SAVED_OTC, otc.getText().toString());
        String s = saveToInternalStorage(photoImage);
        ed.putString(PATH, s);
        ed.commit();
        Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            photoImage = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(photoImage);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
