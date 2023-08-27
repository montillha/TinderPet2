package mary_jhenny.tinderpetdefinitivo.bean;

public class dar_match {
    private int id_deuMatch;
    private int id_recebeu;

    public dar_match(int id_deuMatch, int id_recebeu) {
        this.id_deuMatch = id_deuMatch;
        this.id_recebeu = id_recebeu;
    }

    public int getId_deuMatch() {
        return id_deuMatch;
    }

    public void setId_deuMatch(int id_deuMatch) {
        this.id_deuMatch = id_deuMatch;
    }

    public int getId_recebeu() {
        return id_recebeu;
    }

    public void setId_recebeu(int id_recebeu) {
        this.id_recebeu = id_recebeu;
    }

    public dar_match() {
    }
}
