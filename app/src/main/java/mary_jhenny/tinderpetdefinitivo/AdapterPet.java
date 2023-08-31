package mary_jhenny.tinderpetdefinitivo;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;


import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.bean.Pet;
import mary_jhenny.tinderpetdefinitivo.Foto;

public class AdapterPet extends ArrayAdapter<Pet> {

    private Context context;

    private ArrayList<Pet> pets;
    public AdapterPet(Context context, ArrayList<Pet> pets) {
        super(context, R.layout.item_pet, pets);
        this.context = context;
        this.pets= pets;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_pet, parent, false);
        TextView LblNome = itemView.findViewById(R.id.lblNome);
        TextView lblRaca= itemView.findViewById(R.id.lblRaca);
        TextView lblIdade = itemView.findViewById(R.id.lblIdade);
        TextView lblCidade = itemView.findViewById(R.id.lblCidade);
        TextView lblSexo = itemView.findViewById(R.id.lblSexo);
        ImageView lblFoto = itemView.findViewById(R.id.lblFoto);
        LblNome.setText(pets.get(position).getNome());
        lblRaca.setText(pets.get(position).getRaca());
        lblIdade.setText(pets.get(position).getNascimento());
        lblCidade.setText(pets.get(position).getCidade());
        lblSexo.setText(pets.get(position).getSexo());

        lblFoto.setImageURI(Uri.parse(pets.get(position).getFoto()));
        return itemView;
    }

}
