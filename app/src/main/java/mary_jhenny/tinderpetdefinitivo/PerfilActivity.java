package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class PerfilActivity extends AppCompatActivity {
    private TextView lblAtributos;
    private TextView lblNomePet;
    private Button btnEditar;
    private Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Intent i = getIntent();
        Pet p = (Pet) i.getSerializableExtra("pet");
        int id_pet = i.getIntExtra("id_pet",-1);
        Toast.makeText(this, id_pet+" AAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
        ConexaoDao cdao = new ConexaoDao(PerfilActivity.this);
        lblAtributos = findViewById(R.id.lblAtributos);
        lblNomePet = findViewById(R.id.lblNomePet);
        btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new EscutadorEditar());
        pet = cdao.listarPet(id_pet);
        lblNomePet.setText(p.getNome());
        lblAtributos.setText(p.getRaca()+" | "+p.getEspecie());
        /*pet = cdao.listarPet(id_pet);
        String nome = pet.getNome();
        String especie = pet.getEspecie();
        lblNomePet.setText(nome);
        lblAtributos.setText(pet.getRaca()+" | "+pet.getEspecie());*/

       //teste notificações:

       /* Intent iNoti = new Intent(getApplicationContext(), NotificacaoActivity.class);
        iNoti.putExtra("id_pet",id_pet);
        startActivity(iNoti);*/

    }
    private class EscutadorEditar implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(),EditarPetActivity.class);
            String email= getIntent().getStringExtra("email");
            i.putExtra("activity_name", this.getClass().getName());
            i.putExtra("petEditar",pet.getId());
            i.putExtra("email",email);
            startActivity(i);
            finish();

        }
    }
    public class EscutadorNotificacao implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent iId = getIntent();
            int id_pet = iId.getIntExtra("id_pet",-1);
            Intent i = new Intent(getApplicationContext(),NotificacaoActivity.class);
            i.putExtra("id_pet",id_pet);
            startActivity(i);
            finish();

        }
    }
}