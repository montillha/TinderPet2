package mary_jhenny.tinderpetdefinitivo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class CadastrarPet extends AppCompatActivity {


    private TextView txtNomeP;
    private TextView txtRaca;
    private TextView txtEspecie;
    private TextView txtPorte;
    private TextView txtCidade;
    private TextView txtNasc;
    private TextView txtEstado;
    private TextView txtPedigree;
    private TextView txtDesc;
    private TextView txtSexo;

    private Button btnCadastarP;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pet_teste);
        txtNomeP = findViewById(R.id.txtNomeP);
        txtRaca= findViewById(R.id.txtRaca);
        txtEspecie= findViewById(R.id.txtEspecie);
        txtPorte= findViewById(R.id.txtPorte);
        txtCidade = findViewById(R.id.txtCidade);
        txtNasc= findViewById(R.id.txtNasc);
        txtEstado = findViewById(R.id.txtEstado);
        txtPedigree = findViewById(R.id.txtPedigree);
        txtDesc = findViewById(R.id.txtDesc);
        txtSexo = findViewById(R.id.txtSexo);
        btnCadastarP = findViewById(R.id.btnCadastrarP);
        btnCadastarP.setOnClickListener(new Escutador_CadastrarP());
    }
     private class Escutador_CadastrarP implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String nome = txtNomeP.getText().toString();
            String raca = txtRaca.getText().toString();
            String especie = txtEspecie.getText().toString();
            String porte = txtPorte.getText().toString();
            String cidade= txtCidade.getText().toString();
            String estado= txtEstado.getText().toString();
            String nasc= txtNasc.getText().toString();
            String descricao = txtDesc.getText().toString();
            String pedigree = txtPedigree.getText().toString();
            String sexo = txtSexo.getText().toString();

            Intent i2= getIntent();
            String email = i2.getStringExtra("email");
            ConexaoDao cdao = new ConexaoDao(CadastrarPet.this);
            Pet p = new Pet(nome,especie,raca,sexo,pedigree,nasc,porte,estado,cidade,descricao,email);
            long resultado = cdao.inserirPet(p);

            int id = p.getId();
            Intent i_foto = new Intent(getApplicationContext(), Foto.class);
            i_foto.putExtra("id",id);
            startActivity(i_foto);


        }


    }



}