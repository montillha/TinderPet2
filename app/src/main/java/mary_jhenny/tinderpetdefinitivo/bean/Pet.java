package mary_jhenny.tinderpetdefinitivo.bean;


public class Pet {
    private static  int sequence =0;
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private String sexo;
    private String pedigree;
    private String nascimento;
    private String tamanho;
    private String estado;
    private String cidade;
    private String descricao;
    private String emailTutor;


    public String getEmailTutor() {
        return emailTutor;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pet(String nome, String especie, String raca, String sexo, String pedigree, String nascimento, String tamanho, String estado, String cidade, String descricao, String emailTutor) {
        this.id = sequence++;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.sexo = sexo;
        this.pedigree = pedigree;
        this.nascimento = nascimento;
        this.tamanho = tamanho;
        this.estado = estado;
        this.cidade = cidade;
        this.descricao = descricao;
        this.emailTutor = emailTutor;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                ", raca='" + raca + '\'' +
                ", sexo='" + sexo + '\'' +
                ", pedigree='" + pedigree + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", descricao='" + descricao + '\'' +
                ", emailTutor='" + emailTutor + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPedigree() {
        return pedigree;
    }

    public void setPedigree(String pedigree) {
        this.pedigree = pedigree;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pet() {

    }
}

