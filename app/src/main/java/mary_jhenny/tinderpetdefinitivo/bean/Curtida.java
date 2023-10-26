package mary_jhenny.tinderpetdefinitivo.bean;

public class Curtida {
    private int id_curtiu;
    private int id_recebeu;

    public int getId_curtiu() {
        return id_curtiu;
    }

    public void setId_curtiu(int id_curtiu) {
        this.id_curtiu = id_curtiu;
    }

    public int getId_recebeu() {
        return id_recebeu;
    }

    public void setId_recebeu(int id_recebeu) {
        this.id_recebeu = id_recebeu;
    }

    public Curtida() {
    }

    public Curtida(int id_curtiu, int id_recebeu) {
        this.id_curtiu = id_curtiu;
        this.id_recebeu = id_recebeu;
    }
}
