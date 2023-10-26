package mary_jhenny.tinderpetdefinitivo.bean;

public class Tutor {
    String email;
    String nome;
    String senha;

    @Override
    public String toString() {
        return "Tutor{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public Tutor(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;

    }

    public Tutor() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
