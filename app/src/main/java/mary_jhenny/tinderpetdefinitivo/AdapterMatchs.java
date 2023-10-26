package mary_jhenny.tinderpetdefinitivo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.Dao.ConexaoDao;
import mary_jhenny.tinderpetdefinitivo.bean.Dar_match;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;

public class AdapterMatchs extends ArrayAdapter {
    private Context context;
    private ArrayList<Dar_match> listaMatchs;


    public AdapterMatchs(Context context, ArrayList<Dar_match> listaMatchs) {
        super(context,R.layout.item_match,listaMatchs);
        this.context = context;
        this.listaMatchs = listaMatchs;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li_match = LayoutInflater.from(parent.getContext());
        View itemView = li_match.inflate(R.layout.item_match, parent, false);
        TextView lblPet1 = itemView.findViewById(R.id.lblPet1);
        TextView lblPet2 = itemView.findViewById(R.id.lblPet2);
        // ConexaoDao cdao = new ConexaoDao(AdapterMatchs.this.getContext());//verificar
        // ArrayList<Dar_match> = cdao.listarMatchsRecebidos();

        //  String nome1 = p.getNome();
        //String nome2 = p2.getNome();
        //lblPetCurtiu.setText(nome);
        //return itemView;
        return itemView;
    }

}
