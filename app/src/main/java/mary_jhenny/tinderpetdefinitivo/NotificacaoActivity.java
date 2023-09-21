package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Curtida;
import mary_jhenny.tinderpetdefinitivo.bean.Dar_match;

public class NotificacaoActivity extends AppCompatActivity {
    private ListView listCurtidas;
    private AdapterCurtidas adapterCurtidas;
    private ArrayList<Curtida> curtidas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);
        listCurtidas = findViewById(R.id.listCurtidas);
        Intent i = getIntent();
        int id =i.getIntExtra("id_pet",-1);
        ConexaoDao cdao = new ConexaoDao(NotificacaoActivity.this);
        curtidas= cdao.listarCurtidasRecebidas(id);
        adapterCurtidas= new AdapterCurtidas(this,curtidas);
        listCurtidas.setAdapter(adapterCurtidas);


    }
}