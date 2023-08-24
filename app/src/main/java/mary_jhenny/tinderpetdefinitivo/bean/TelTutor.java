package mary_jhenny.tinderpetdefinitivo.bean;

public class TelTutor {
    private String email;
    private String telefone;

    public TelTutor() {
    }

    public TelTutor(String email, String telefone) {
        this.email = email;
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Telefone_tutor{" +
                "email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
