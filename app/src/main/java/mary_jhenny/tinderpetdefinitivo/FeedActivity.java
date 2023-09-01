package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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
        listaPets = findViewById(R.id.listaPets);
        ConexaoDao cdao = new ConexaoDao(FeedActivity.this);
        pets = cdao.listarPets();
        adapterPet = new AdapterPet(this,pets);
        listaPets.setAdapter(adapterPet);

    }
}