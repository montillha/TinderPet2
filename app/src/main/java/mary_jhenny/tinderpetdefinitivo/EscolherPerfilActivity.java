package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class EscolherPerfilActivity extends AppCompatActivity {
    private ListView listaPerfis;
    private ArrayList<Pet> perfilPet = new ArrayList<Pet>();

    private Button btnNovoPet;
    private AdapterPerfilsPet adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_perfil);
        Intent i = getIntent();
        String email = i.getStringExtra("email");
        listaPerfis = findViewById(R.id.listaPerfis);
        btnNovoPet = findViewById(R.id.btnNovoPet);
        btnNovoPet.setOnClickListener(new EscutadorNovoPet());
        ConexaoDao cdao = new ConexaoDao(EscolherPerfilActivity.this);
        perfilPet =cdao.listarPetsTutor(email);
        adapter = new AdapterPerfilsPet(this,perfilPet);
        listaPerfis.setAdapter(adapter);
        listaPerfis.setOnItemClickListener( new EscutadorLista() );

    }
    private class EscutadorNovoPet implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent i_email = getIntent();
            String email = i_email.getStringExtra("email");
            Intent i = new Intent(getApplicationContext(),CadastrarPetActivity.class);
            i.putExtra("activity_name", this.getClass().getName());
            startActivity(i);
            finish();

        }
    }
   private class EscutadorLista implements AdapterView.OnItemClickListener {
       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           ConexaoDao cdao = new ConexaoDao(EscolherPerfilActivity.this);
           Pet p = perfilPet.get(i);
           int id_pet = p.getId();
           Intent intent = new Intent(getApplicationContext(), PerfilPet.class);
           intent.putExtra("id_pet",id_pet);
           startActivity(intent);
           finish();

       }

   }


}