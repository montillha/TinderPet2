package mary_jhenny.tinderpetdefinitivo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Curtida;
import mary_jhenny.tinderpetdefinitivo.bean.Dar_match;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;
import mary_jhenny.tinderpetdefinitivo.bean.TelTutor;

public class AdapterPet extends ArrayAdapter<Pet> {

    private Context context;

    private ArrayList<Pet> pets;
    private Pet petFeed;
    private Pet petPerfil;

    public AdapterPet(Context context, ArrayList<Pet> pets,Pet petPerfil ) {
        super(context, R.layout.item_pet, pets);
        this.context = context;
        this.pets = pets;
        this.petPerfil = petPerfil;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        petFeed = pets.get(position);
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_pet, parent, false);
        TextView LblNome = itemView.findViewById(R.id.lblNome);
        TextView lblRaca = itemView.findViewById(R.id.lblRaca);
        TextView lblIdade = itemView.findViewById(R.id.lblIdade);
        TextView lblCidade = itemView.findViewById(R.id.lblCidade);
        TextView lblSexo = itemView.findViewById(R.id.lblSexo);
        ImageView lblFoto = itemView.findViewById(R.id.lblFoto);
        Button btnCurtir = itemView.findViewById(R.id.btnCurtir);
        Button btnTel = itemView.findViewById(R.id.btnTel);
        btnTel.setOnClickListener(new EscutadorTel());
        btnCurtir.setOnClickListener(new EscutadorCurtida());
        Button btnMatch = itemView.findViewById(R.id.btnMatch);
        btnMatch.setOnClickListener( new EscutadorMatch());
        LblNome.setText(petFeed.getNome());
        lblRaca.setText(petFeed.getRaca());
        lblIdade.setText(petFeed.getNascimento());
        lblCidade.setText(petFeed.getCidade());
        lblSexo.setText(petFeed.getSexo());
        //lblFoto.setImageResource(R.drawable.dog);
        //return itemView;

        Uri uriFoto = Uri.parse(petFeed.getFoto());
        try {
            final InputStream imageStream = context.getContentResolver().openInputStream(uriFoto);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            lblFoto.setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return itemView;
    }
    private class EscutadorCurtida implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            ConexaoDao cdao = new ConexaoDao(AdapterPet.this.getContext());
            int idCurtiu = petPerfil.getId();
            int idRecebeu = petFeed.getId();
            Curtida c = new Curtida(idCurtiu,idRecebeu);
            cdao.inserirCurtida(c);
        }
    }
    private class EscutadorMatch implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            ConexaoDao cdao= new ConexaoDao(AdapterPet.this.getContext());
            int id_petPerfil = petPerfil.getId();
            int id_petMatch = petPerfil.getId();
            Dar_match  d = new Dar_match(id_petPerfil,id_petMatch);
            cdao.inserirMatch(d);
        }
    }
    private class EscutadorTel implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            ConexaoDao cdao = new ConexaoDao(AdapterPet.this.getContext());
            String emailTutor = petFeed.getEmailTutor();
            TelTutor telTutor =cdao.listarTel_tutor(emailTutor);
            String tel = telTutor.getTelefone();
            Toast.makeText(context, "tel: "+tel, Toast.LENGTH_LONG).show();
        }
    }

}
