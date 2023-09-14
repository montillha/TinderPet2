package mary_jhenny.tinderpetdefinitivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
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
    private String uri_foto;
    private ArrayList<Pet> listaPets = new ArrayList<>();

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
    private class AcessarFeed implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent i_activity = getIntent();
            String activity = i_activity.getStringExtra("activity");
            System.out.println(activity+"MMMMMMMMMMM");
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
            ConexaoDao cdao = new ConexaoDao(FotoActivity.this);
            Pet p = new Pet(nome,especie,raca,sexo,pedigree,nasc,porte,estado,cidade,descricao,uri_foto,email);
            cdao.inserirPet(p);
            Intent i_feed = new Intent(getApplicationContext(), FeedActivity.class);
            startActivity(i_feed);

            /*if(activity!=null){
                Intent i_escolherPerfil= new Intent(getApplicationContext(),EscolherPerfilActivity.class);
                i_escolherPerfil.putExtra("activity_name", this.getClass().getName());
                i_activity.putExtra("pet",p);
                startActivity(i_escolherPerfil);
                //setResult(RESULT_OK,i_activity);
            }else{
                Intent i_feed = new Intent(getApplicationContext(), FeedActivity.class);
                startActivity(i_feed);

            }
            finish();*/


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
        startActivityForResult(intent,1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1000) {
            if (resultCode == RESULT_OK && requestCode == 1000) {
                Uri selectedImageUri = data.getData();
                String realPath = getRealPath(this, data.getData());
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    if (inputStream != null) {
                        File imageFile = saveImageToExternalStorage(inputStream);
                        if (imageFile != null) {
                            uri_foto = imageFile.getAbsolutePath();
                            imgteste.setImageURI(selectedImageUri);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //ConexaoDao cdao = new ConexaoDao(Foto.this);
            //uri_foto= data.getData().toString();
            //imgteste.setImageURI(data.getData());


        }
    }
    private File saveImageToExternalStorage(InputStream inputStream) throws IOException {
        File storageDir = new File(Environment.getExternalStorageDirectory(), "imagens");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + ".jpg";

        File imageFile = new File(storageDir, imageFileName);
        OutputStream outputStream = new FileOutputStream(imageFile);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();

        return imageFile;
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

    private String getRealPath(Context context, Uri uri){
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }

        cursor.close();

        return filePath;
    }
}