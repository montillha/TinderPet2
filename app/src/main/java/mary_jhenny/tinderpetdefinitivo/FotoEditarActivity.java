package mary_jhenny.tinderpetdefinitivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class FotoEditarActivity extends AppCompatActivity {

    private Button btnAcessar;
    private Button btnFoto;
    private ImageView imgTeste;
    private EditText txtEditarPet;
    private Uri uriFoto;

    private ArrayList<Pet> listaPets = new ArrayList<>();

    private final int ESCOLHER_IMAGEM_REQUEST_CODE = 1000;
    private final int SOLICITAR_PERMISSAO_ARMAZENAMENTO_EXTERNO_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        btnAcessar = findViewById(R.id.btnAcessarT);
        btnFoto = findViewById(R.id.btnFoto);
        imgTeste = findViewById(R.id.imgTeste);
        txtEditarPet = findViewById(R.id.txtEditarPet);
        btnFoto.setOnClickListener(new EscutadorFoto());
        btnAcessar.setOnClickListener(new AcessarFeed());

    }

    private class AcessarFeed implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Pet pet = (Pet) getIntent().getSerializableExtra("pet");
            int  petEditar = getIntent().getIntExtra("petEditar",-1);
            Toast.makeText(FotoEditarActivity.this, petEditar+" aaaaaaaa", Toast.LENGTH_LONG).show();
            String activity_name = getIntent().getStringExtra("activity_name");
            String desc =txtEditarPet.getText().toString();
            pet.setFoto(uriFoto.toString());
            pet.setDescricao(desc);
            ConexaoDao cdao = new ConexaoDao(FotoEditarActivity.this);
            cdao.alterarPet(pet,petEditar);
            Intent iFeed = new Intent(getApplicationContext(),PerfilActivity.class);
            iFeed.putExtra("pet",pet);
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
                imgTeste.setImageBitmap(selectedImage);
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