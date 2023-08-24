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

public class CadastrarTutor extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtTelefone;
    private TextView txtEmail;
    private TextView txtSenha;
    private Button btnCadastrarT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tutor_teste);
        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        btnCadastrarT = findViewById(R.id.btnContinuar);
        btnCadastrarT.setOnClickListener(new Escutador_CadastrarT());

    }
    class Escutador_CadastrarT implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ConexaoDao cdao = new ConexaoDao(CadastrarTutor.this);
            String nome = txtNome.getText().toString();
            String telefone  = txtTelefone.getText().toString();
            String email  = txtEmail.getText().toString();
            String senha  = txtSenha.getText().toString();
            Tutor t  = new Tutor(email,nome,senha);
            TelTutor tel = new TelTutor(email,telefone);
            long resultado= cdao.inserirTutor(t);


            Intent i = new Intent(getApplicationContext(),CadastrarPet.class);
            i.putExtra("email", email);

            if (resultado ==-1){
                Toast.makeText(CadastrarTutor.this, "Erro ao inserir tutor", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(CadastrarTutor.this, "Tutor inserido com sucesso", Toast.LENGTH_SHORT).show();
                cdao.inserirTel_tutor(tel);
                //erro previsto

                Intent I = new Intent(getApplicationContext(), CadastrarPet.class);
                startActivity(I);

            }




        }
    }
}