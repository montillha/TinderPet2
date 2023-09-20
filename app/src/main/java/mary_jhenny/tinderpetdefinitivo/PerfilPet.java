package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class PerfilPet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_pet);
        Intent i = getIntent();
        int id_pet = i.getIntExtra("id_pet",-1);
        ConexaoDao cdao = new ConexaoDao(PerfilPet.this);
        Pet pet = cdao.listarPet(id_pet);
        String nome = pet.getNome();
        String especie = pet.getEspecie();
        Toast.makeText(this, nome +" ..."+especie, Toast.LENGTH_SHORT).show();
        //teste notificações:
        Intent iNoti = new Intent(getApplicationContext(),NotificacoesActivity.class);
        iNoti.putExtra("id_pet",id_pet);
        startActivity(iNoti);


    }
}