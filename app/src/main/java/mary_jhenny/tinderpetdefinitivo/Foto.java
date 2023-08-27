package mary_jhenny.tinderpetdefinitivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class Foto extends AppCompatActivity {

    private Button btnFinalizar;
    private Button btnFoto;
    private ImageView imgteste;
    private String uri_foto;
    private ArrayList<Pet> listaPets = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_teste);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnFoto = findViewById(R.id.btnFoto);
        imgteste = findViewById(R.id.imgteste);
        btnFoto.setOnClickListener(new EscutadorFoto());
        btnFinalizar.setOnClickListener(new AcessarFeed());

    }
    private class AcessarFeed implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent i = getIntent();
            String nome= i.getStringExtra("nome");
            String raca= i.getStringExtra("raca");
            String especie = i.getStringExtra("especie");
            String porte = i.getStringExtra("porte");
            String cidade= i.getStringExtra("cidade");
            String estado= i.getStringExtra("estado");
            String nasc= i.getStringExtra("nasc");
            String descricao= i.getStringExtra("descricao");
            String pedigree= i.getStringExtra("pedigree");
            String sexo= i.getStringExtra("sexo");
            String email =i.getStringExtra("email");
            ConexaoDao cdao = new ConexaoDao(Foto.this);
            Pet p = new Pet(nome,especie,raca,sexo,pedigree,nasc,porte,estado,cidade,descricao,uri_foto,email);
            cdao.inserirPet(p);
            Intent i_feed = new Intent(getApplicationContext(), Feed.class);
            startActivity(i_feed);
            finish();


        }
    }
    //link do código de refêrencia para manipular o cadastro de fotos:https://www.youtube.com/watch?v=S4GFNZoxag8
    private class EscutadorFoto implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                String[] permissao= {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissao,1001);
            }else{
                escolherimg();
            }
        }
    }
    private void escolherimg(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1000) {
            ConexaoDao cdao = new ConexaoDao(Foto.this);
            uri_foto= data.getData().toString();
            imgteste.setImageURI(data.getData());


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1001:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    escolherimg();
                } else {
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
                }
        }
    }
}