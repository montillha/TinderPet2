package mary_jhenny.tinderpetdefinitivo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class AdapterPerfilsPet extends ArrayAdapter<Pet> {
    private Context context;
    private ArrayList<Pet> perfilsPet;


    public AdapterPerfilsPet(@NonNull Context context, ArrayList<Pet> perfilsPet) {
        super(context, R.layout.item_perfils_pet, perfilsPet);
        this.context = context;
        this.perfilsPet = perfilsPet;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_perfils_pet, parent, false);
        TextView lblNomePerfil = itemView.findViewById(R.id.lblNomePerfil);
        lblNomePerfil.setText(perfilsPet.get(position).getNome());
        return itemView;
    }
}
