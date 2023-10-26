package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.TelTutor;
import mary_jhenny.tinderpetdefinitivo.bean.Tutor;

public class CadastrarTutorActivity extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtTelefone;
    private TextView txtEmail;
    private TextView txtSenhaT;
    private Button btnCadastrarT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tutor);
        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEmail= findViewById(R.id.txtEmail);
        txtSenhaT = findViewById(R.id.txtSenhaT);
        btnCadastrarT = findViewById(R.id.btnContinuar);
        btnCadastrarT.setOnClickListener(new Escutador_CadastrarT());

    }
    class Escutador_CadastrarT implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ConexaoDao cdao = new ConexaoDao(CadastrarTutorActivity.this);
            String nome = txtNome.getText().toString();
            String email = txtEmail.getText().toString();
            String telefone  = txtTelefone.getText().toString();
            String senha  = txtSenhaT.getText().toString();
            Tutor t  = new Tutor(email,nome,senha);
            TelTutor tel = new TelTutor(email,telefone);
            long resultado= cdao.inserirTutor(t);

            if (resultado ==-1){
                Toast.makeText(CadastrarTutorActivity.this, "Erro ao inserir tutor", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(CadastrarTutorActivity.this, "Tutor inserido com sucesso", Toast.LENGTH_SHORT).show();
                cdao.inserirTel_tutor(tel);
                Intent i = new Intent(getApplicationContext(), CadastrarPetActivity.class);
                i.putExtra("email", email);
                startActivity(i);

            }
           finish();




        }
    }
}