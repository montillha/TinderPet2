package mary_jhenny.tinderpetdefinitivo.bean;

public class ImgPet {
    private int id;
    private String foto;

    public ImgPet(int id, String foto) {
        this.id = id;
        this.foto = foto;
    }

    public ImgPet() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    @Override
    public String toString() {
        return "Foto_pet{" +
                "id=" + id +
                ", foto='" + foto + '\'' +
                '}';
    }
}

