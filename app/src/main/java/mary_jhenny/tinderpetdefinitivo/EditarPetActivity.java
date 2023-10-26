package mary_jhenny.tinderpetdefinitivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class EditarPetActivity extends AppCompatActivity {
    private TextView txtNomeP;
    private TextView txtRaca;
    private TextView txtEspecie;
    private TextView txtPorte;
    private TextView txtCidade;
    private TextView txtNasc;
    private TextView txtEstado;
    private TextView txtPedigree;
    private TextView txtSexo;

    private Button btnEditarP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pet);
        txtNomeP = findViewById(R.id.txtNomeP);
        txtRaca= findViewById(R.id.txtRaca);
        txtEspecie= findViewById(R.id.txtEspecie);
        txtPorte= findViewById(R.id.txtPorte);
        txtCidade = findViewById(R.id.txtCidade);
        txtNasc= findViewById(R.id.txtNasc);
        txtEstado = findViewById(R.id.txtEstado);
        txtPedigree = findViewById(R.id.txtPedigree);
        txtSexo = findViewById(R.id.txtSexo);
        btnEditarP = findViewById(R.id.btnEditarP);
        btnEditarP.setOnClickListener(new EscutadorEditarP());

    }
    private class EscutadorEditarP implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            String email =intent.getStringExtra("email");
            int petEditar = intent.getIntExtra("petEditar",-1);
            String nome = txtNomeP.getText().toString();
            String raca = txtRaca.getText().toString();
            String especie = txtEspecie.getText().toString();
            String porte = txtPorte.getText().toString();
            String cidade= txtCidade.getText().toString();
            String estado= txtEstado.getText().toString();
            String nasc= txtNasc.getText().toString();
            String pedigree = txtPedigree.getText().toString();
            String sexo = txtSexo.getText().toString();
            // String activity = getIntent().getStringExtra("activity_name");
            Intent i = new Intent(getApplicationContext(), FotoEditarActivity.class);
            Pet pet = new Pet(nome, especie, raca, sexo, pedigree, nasc, porte, estado, cidade,null, null,email);
            // i.putExtra("activity_name",activity_name);
            i.putExtra("pet",pet);
            i.putExtra("petEditar",petEditar);
           // i.putExtra("email",email);
            startActivity(i);
            finish();

        }
    }

}