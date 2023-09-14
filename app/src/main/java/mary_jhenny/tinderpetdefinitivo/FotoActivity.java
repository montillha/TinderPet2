package mary_jhenny.tinderpetdefinitivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class FotoActivity extends AppCompatActivity {

    private Button btnFinalizar;
    private Button btnFoto;
    private ImageView imgteste;
    private Uri uriFoto;

    private ArrayList<Pet> listaPets = new ArrayList<>();

    private final int ESCOLHER_IMAGEM_REQUEST_CODE = 1000;
    private final int SOLICITAR_PERMISSAO_ARMAZENAMENTO_EXTERNO_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnFoto = findViewById(R.id.btnFoto);
        imgteste = findViewById(R.id.imgteste);
        btnFoto.setOnClickListener(new EscutadorFoto());
        btnFinalizar.setOnClickListener(new AcessarFeed());

    }

    private class AcessarFeed implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Pet pet = (Pet) getIntent().getSerializableExtra("pet");
            pet.setFoto(uriFoto.toString());
            ConexaoDao cdao = new ConexaoDao(FotoActivity.this);
            cdao.inserirPet(pet);
            Intent iFeed = new Intent(getApplicationContext(), FeedActivity.class);
            startActivity(iFeed);
            finish();
        }
    }


    //link do código de refêrencia para manipular o cadastro de fotos:https://www.youtube.com/watch?v=S4GFNZoxag8
    private class EscutadorFoto implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissao = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissao, SOLICITAR_PERMISSAO_ARMAZENAMENTO_EXTERNO_REQUEST_CODE);
            } else {
                escolherimg();
            }
        }
    }

    private void escolherimg() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, ESCOLHER_IMAGEM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ESCOLHER_IMAGEM_REQUEST_CODE) {
            uriFoto = data.getData();
            try {
                final InputStream imageStream = getContentResolver().openInputStream(uriFoto);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgteste.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case SOLICITAR_PERMISSAO_ARMAZENAMENTO_EXTERNO_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    escolherimg();
                } else {
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
                }
        }
    }
}