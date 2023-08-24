package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private Button btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new Cadastrar());
        btnEntrar.setOnClickListener(new Login());

    }

    class Cadastrar implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            ConexaoDao cdao = new ConexaoDao(MainActivity.this);
            System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

            System.out.println(cdao.listarPets());
            Intent i = new Intent(getApplicationContext(), CadastrarTutor.class);
            startActivity(i);
        }
    }

    class Login implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            Intent i = new Intent(getApplicationContext(), Logar.class);
           startActivity(i);
        }
    }
}