package mary_jhenny.tinderpetdefinitivo;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class FeedActivity extends AppCompatActivity {
    private ListView listaPets;
    private AdapterPet adapterPet;
    private ArrayList<Pet> pets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        continuarComFeed();

    }

    private void continuarComFeed() {
        Intent i = getIntent();
        Pet p = (Pet)i.getSerializableExtra("pet");
       //int id = i.getIntExtra("id",-1);
        listaPets = findViewById(R.id.listaPets);
        ConexaoDao cdao = new ConexaoDao(FeedActivity.this);
        System.out.println(p+"CHEGUEI");
        pets = cdao.listarPets();
        adapterPet = new AdapterPet(this,pets,p);
        listaPets.setAdapter(adapterPet);
    }
}