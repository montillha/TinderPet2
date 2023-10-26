package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class CadastrarPetActivity extends AppCompatActivity {

    private TextView txtNomeP;
    private TextView txtRaca;
    private TextView txtEspecie;
    private TextView txtPorte;
    private TextView txtCidade;
    private TextView txtNasc;
    private TextView txtEstado;
    private TextView txtPedigree;

    private TextView txtSexo;

    private Button btnCadastrarP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pet);
        txtNomeP = findViewById(R.id.txtNomeP);
        txtRaca= findViewById(R.id.txtRaca);
        txtEspecie= findViewById(R.id.txtEspecie);
        txtPorte= findViewById(R.id.txtPorte);
        txtCidade = findViewById(R.id.txtCidade);
        txtNasc= findViewById(R.id.txtNasc);
        txtEstado = findViewById(R.id.txtEstado);
        txtPedigree = findViewById(R.id.txtPedigree);
        txtSexo = findViewById(R.id.txtSexo);
        btnCadastrarP = findViewById(R.id.btnAcessarT);
        btnCadastrarP.setOnClickListener(new Escutador_CadastrarP());
    }
     private class Escutador_CadastrarP implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent i_tutor = getIntent();
            String email =i_tutor.getStringExtra("email");
            String nome = txtNomeP.getText().toString();
            String raca = txtRaca.getText().toString();
            String especie = txtEspecie.getText().toString();
            String porte = txtPorte.getText().toString();
            String cidade= txtCidade.getText().toString();
            String estado= txtEstado.getText().toString();
            String nasc= txtNasc.getText().toString();
            String pedigree = txtPedigree.getText().toString();
            String sexo = txtSexo.getText().toString();
            Intent i = new Intent(getApplicationContext(), FotoActivity.class);
            Pet pet = new Pet(nome, especie, raca, sexo, pedigree, nasc, porte, estado, cidade,null, null, email);
            i.putExtra("pet",pet);
            startActivity(i);
            finish();


        }


    }



}