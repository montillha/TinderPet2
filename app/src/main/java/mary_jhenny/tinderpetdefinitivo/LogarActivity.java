package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;

public class LogarActivity extends AppCompatActivity {
    private Button btnAcessarT;
    private TextView txtEmailLogin;
    private TextView txtSenhaLogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);
        btnAcessarT= findViewById(R.id.btnAcessarT);
        txtEmailLogin= findViewById(R.id.txtEmailLogin);
        txtSenhaLogin= findViewById(R.id.txtSenhaLogin);
        btnAcessarT.setOnClickListener(new AcessarFeed());

    }
    class AcessarFeed implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ConexaoDao cdao = new ConexaoDao(LogarActivity.this);
            String email = txtEmailLogin.getText().toString();
            String senha = txtSenhaLogin.getText().toString();
            boolean achou = cdao.verificaTutor(email,senha);
            if (achou){
                Intent i = new Intent( getApplicationContext(), EscolherPerfilActivity.class );
                i.putExtra("email",email);
                startActivity(i);
                finish();

            }else{
                Toast.makeText(LogarActivity.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
            }

        }
    }
}