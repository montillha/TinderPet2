package mary_jhenny.tinderpetdefinitivo;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Curtida;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;


public class AdapterCurtidas extends ArrayAdapter {
    private Context context;
    private ArrayList<Curtida> listaCurtidas;
    private int id;
    public AdapterCurtidas(Context context, ArrayList<Curtida> listaCurtidas) {
        super(context,R.layout.item_curtida,listaCurtidas);
        this.context = context;
        this.listaCurtidas = listaCurtidas;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li_curtida = LayoutInflater.from(parent.getContext());
        View itemView = li_curtida.inflate(R.layout.item_curtida, parent, false);
        TextView lblPetCurtiu = itemView.findViewById(R.id.lblPetCurtiu);
        ConexaoDao cdao = new ConexaoDao(AdapterCurtidas.this.getContext());//verificar
        Curtida c = listaCurtidas.get(position);
        Pet p =  cdao.listarPet(listaCurtidas.get(position).getId_curtiu());
        String nome = p.getNome();
        lblPetCurtiu.setText(nome);
        return itemView;
    }



}
