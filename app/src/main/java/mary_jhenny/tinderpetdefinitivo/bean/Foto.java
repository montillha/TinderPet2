package mary_jhenny.tinderpetdefinitivo.bean;

public class Foto {
    private int id;
    private String uri;

    public Foto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Foto(int id, String uri) {
        this.id = id;
        this.uri = uri;
    }
}
