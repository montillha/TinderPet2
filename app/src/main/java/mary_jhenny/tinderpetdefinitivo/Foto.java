package mary_jhenny.tinderpetdefinitivo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;
import mary_jhenny.tinderpetdefinitivo.bean.Foto;

public class Foto extends AppCompatActivity {

    private Button btnFinalizar;
    private Button btnFoto;
    private ImageView imgteste;
    private String uridata;
    private ArrayList<Pet> listaPets = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_teste);


        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnFoto = findViewById(R.id.btnFoto);
        imgteste = findViewById(R.id.imgteste);
        btnFinalizar.setOnClickListener(new AcessarFeed());
        btnFoto.setOnClickListener(new EscutadorFoto());

    }
     private class AcessarFeed implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ConexaoDao cdao = new ConexaoDao(Foto.this);
            Intent i = new Intent( getApplicationContext(), Feed.class );
            startActivity(i);
            String foto ="hhhhh";




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
        if (resultCode == RESULT_OK && requestCode == 1000) {
            Intent i = getIntent();
            String uri_foto = String.valueOf(data.getData());
            int id= Integer.parseInt(i.getStringExtra("id"));
            Foto foto = new Foto(id,uri_foto);
            imgteste.setImageURI(data.getData());
            uridata = data.getData().toString();


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions, @NonNull int[] grantResults) {
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