package mary_jhenny.tinderpetdefinitivo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mary_jhenny.tinderpetdefinitivo.bean.ImgPet;
import mary_jhenny.tinderpetdefinitivo.bean.Pet;
import mary_jhenny.tinderpetdefinitivo.bean.TelTutor;
import mary_jhenny.tinderpetdefinitivo.bean.Tutor;


public class ConexaoDao {
    //tutor
    private final int COLUNA_EMAIL = 0;
    private final int COLUNA_NOME = 1;
    private final int COLUNA_SENHA = 2;
    // pet
    private final int COLUNA_ID = 0;
    private final int COLUNA_NOMEP = 1;
    private final int COLUNA_ESPECIE= 2;
    private final int COLUNA_RACA = 3;
    private  final int COLUNA_SEXO =4;
    private final int COLUNA_PEDIGREE = 5;
    private final int COLUNA_NASCIMENTO = 6 ;
    private final int COLUNA_TAMANHO =7;
    private final int COLUNA_ESTADO = 8;
    private final int COLUNA_CIDADE= 9;
    private final int COLUNA_DESCRICAO = 10;
    private final int COLUNA_EMAILTUTOR = 11;

    //telTutor
    private final int COLUNA_TELEFONE=1;
     //imgPet
    private final int COLUNA_IMG = 1;

    private SQLiteDatabase db;

    public ConexaoDao(Context context) {
        db = context.openOrCreateDatabase("TinderPet", Context.MODE_PRIVATE, null);

        // Criando tabela Pet
        db.execSQL("CREATE TABLE IF NOT EXISTS pet(" +
                "id INTEGER PRIMARY KEY /*AUTOINCREMENT*/, " +
                "nome TEXT, " +
                "especie TEXT, " +
                "raca TEXT, " +
                "sexo TEXT, " +
                "pedigree TEXT, " +
                "nascimento TEXT, " +
                "tamanho TEXT, " +
                "estado TEXT, " +
                "cidade TEXT, " +
                "descricao TEXT, " +
                "emailTutor TEXT,"+
                "FOREIGN KEY (emailTutor) REFERENCES TUTOR(email));"
        );

        // Criando tabela Tutor
        db.execSQL( "CREATE TABLE IF NOT EXISTS tutor( email TEXT  PRIMARY KEY UNIQUE ," +
                " nome  TEXT," +
                " senha TEXT);"
        );

        //Criando a tabela telTutor
        db.execSQL(" CREATE TABLE IF NOT EXISTS telTutor(email TEXT," +
                "telefone TEXT," +
                "PRIMARY KEY(email,telefone)," +
                "FOREIGN KEY (email) REFERENCES TUTOR(email));"
        );


        //Criando a tabela fotoPet
        db.execSQL("CREATE TABLE IF NOT EXISTS imgPet(id INTEGER," +
                " foto TEXT  ," +
                " PRIMARY KEY(id,foto)," +
                "FOREIGN KEY(id)REFERENCES PET(id));"
        );


        //Criando a tabela curtida
        db.execSQL("CREATE TABLE IF NOT EXISTS curtida(id_curtiu INTEGER," +
                " id_recebeu INTEGER ," +
                " PRIMARY KEY(id_curtiu,id_recebeu)," +
                "FOREIGN KEY(id_curtiu)REFERENCES PET(id)," +
                "FOREIGN KEY(id_recebeu)REFERENCES PET(id));"
        );
        //Criando a tabela dar_match
        db.execSQL("CREATE TABLE IF NOT EXISTS dar_match(id_deuMatch INTEGER," +
                " id_recebeu INTEGER ," +
                " PRIMARY KEY(id_deuMatch,id_recebeu)," +
                "FOREIGN KEY(id_deuMatch)REFERENCES PET(id)," +
                "FOREIGN KEY(id_recebeu)REFERENCES PET(id));"
        );



    }
    // PET
    public long inserirPet(Pet p){
        ContentValues valores;
        valores= new ContentValues();
        //valores.put("id", p.getId());
        valores.put("nome", p.getNome());
        valores.put("especie", p.getEspecie());
        valores.put("raca", p.getRaca());
        valores.put("sexo", p.getSexo());
        valores.put("pedigree", p.getPedigree());
        valores.put("nascimento",p.getNascimento());
        valores.put("tamanho", p.getTamanho());
        valores.put("estado",p.getEstado());
        valores.put("cidade",p.getCidade());
        valores.put("descricao", p.getDescricao());
        valores.put("emailTutor", p.getEmailTutor());
        long resultado = db.insert("pet", null, valores);
        return resultado;
    }
    public int deletarPet(Pet p){
        String where = "id="+p.getId();
        return db.delete("pet",where,null);
    }
    public int alterarPet(Pet p){
        ContentValues valores;
        valores = new ContentValues();
        String where = "id="+p.getId();
        valores.put("id", p.getId());
        valores.put("nome", p.getNome());
        valores.put("especie", p.getEspecie());
        valores.put("raca", p.getRaca());
        valores.put("sexo", p.getSexo());
        valores.put("pedigree", p.getPedigree());
        valores.put("nascimento",p.getNascimento());
        valores.put("tamanho", p.getTamanho());
        valores.put("estado",p.getEstado());
        valores.put("cidade",p.getCidade());
        valores.put("descricao", p.getDescricao());
        valores.put("emailTutor", p.getEmailTutor());
        int resultado = db.update("pet", valores, where,null);
        return resultado;
    }
    public ArrayList<Pet> listarPets(){
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM pet",null);
        ArrayList<Pet> listaPets = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = Integer.parseInt(cursor.getString(COLUNA_ID));
            String nomeP = cursor.getString(COLUNA_NOMEP);
            String especie= cursor.getString(COLUNA_ESPECIE);
            String raca= cursor.getString(COLUNA_RACA);
            String sexo= cursor.getString(COLUNA_SEXO);
            String pedigree = cursor.getString(COLUNA_PEDIGREE);
            String nascimento= cursor.getString(COLUNA_NASCIMENTO);
            String tamanho= cursor.getString(COLUNA_TAMANHO);
            String estado= cursor.getString(COLUNA_ESTADO);
            String cidade= cursor.getString(COLUNA_CIDADE);
            String descricao= cursor.getString(COLUNA_DESCRICAO);
            String emailtutor= cursor.getString(COLUNA_EMAILTUTOR);


            Pet pet = new Pet(nomeP,especie,raca,sexo,pedigree,nascimento,tamanho,estado,cidade,descricao,emailtutor);

            listaPets.add(pet);
        }

        return listaPets;

    }
    public Pet listarPet(int i){
        Cursor cursor;
        Pet p = new Pet();
        cursor = db.rawQuery("SELECT * FROM pet WHERE id="+i,null);
        while (cursor.moveToNext()) {
            int id = Integer.parseInt(cursor.getString(COLUNA_ID));
            String nomeP = cursor.getString(COLUNA_NOMEP);
            String especie= cursor.getString(COLUNA_ESPECIE);
            String raca= cursor.getString(COLUNA_RACA);
            String sexo= cursor.getString(COLUNA_SEXO);
            String pedigree = cursor.getString(COLUNA_PEDIGREE);
            String nascimento= cursor.getString(COLUNA_NASCIMENTO);
            String tamanho= cursor.getString(COLUNA_TAMANHO);
            String estado= cursor.getString(COLUNA_ESTADO);
            String cidade= cursor.getString(COLUNA_CIDADE);
            String descricao= cursor.getString(COLUNA_DESCRICAO);
            String emailtutor= cursor.getString(COLUNA_EMAILTUTOR);
            p.setId(id);
            p.setNome(nomeP);
            p.setEspecie(especie);
            p.setRaca(raca);
            p.setSexo(sexo);
            p.setPedigree(pedigree);
            p.setNascimento(nascimento);
            p.setTamanho(tamanho);
            p.setEstado(estado);
            p.setCidade(cidade);
            p.setDescricao(descricao);
            p.setEmailTutor(emailtutor);
        }
        return p;

    }


    // TUTOR
    public long inserirTutor(Tutor t ){
        ContentValues valores;
        valores = new ContentValues();
        valores.put("email", t.getEmail());
        valores.put("nome", t.getNome());
        valores.put("senha", t.getSenha());

        long resultado = db.insert("tutor", null, valores);

        return resultado;
    }

    public int deletarTutor(Tutor t){
        String where = "email =" + t.getEmail();
        return db.delete("tutor", where,null);
    }


    public int alterarTutor(Tutor t){
        ContentValues valores;
        valores = new ContentValues();
        String where = "email ="+t.getEmail();
        valores.put("nome",t.getNome());
        valores.put("senha",t.getSenha());
        int resultado = db.update("tutor",valores,where,null);
        return resultado;
    }

    public ArrayList<Tutor> listarTutores(){
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM tutor", null);

        ArrayList<Tutor> listaTutores = new ArrayList<>();

        while (cursor.moveToNext()) {
            String email = cursor.getString(COLUNA_EMAIL);
            String nome = cursor.getString(COLUNA_NOME);
            String senha = cursor.getString(COLUNA_SENHA);
            Tutor tutor = new Tutor(email, nome, senha);
            listaTutores.add(tutor);
        }

        return listaTutores;
    }

    public boolean verificaTutor(String emailVerifica, String senhaVerifica) {
        Cursor cursor;
        String[] selectionArgs = { emailVerifica, senhaVerifica };
        String query = "SELECT * FROM tutor WHERE email=? AND senha=?";

        cursor = db.rawQuery(query, selectionArgs);

        boolean tutorExists = cursor.moveToFirst();
        cursor.close();

        return tutorExists;
    }





    //telTutor
    public long inserirTel_tutor(TelTutor telTutor){
        ContentValues valores;
        valores = new ContentValues();
        valores.put("email", telTutor.getEmail());
        valores.put("telefone", telTutor.getTelefone());
        long resultado = db.insert("telTutor", null, valores);
        return resultado;
    }
    public int deletarTel_tutor(TelTutor telTutor){
        String where = "email =" + telTutor.getEmail();
        return db.delete("telTutor", where,null);
    }

    public int alterarTel_tutor(TelTutor telTutor){
        ContentValues valores;
        valores = new ContentValues();
        String where = "email ="+telTutor.getEmail();
        valores.put("telefone",telTutor.getTelefone());
        int resultado = db.update("telTutor",valores,where,null);
        return resultado;
    }

    public ArrayList<TelTutor> listarTel_tutores(){
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM telTutor", null);

        ArrayList<TelTutor> listaTel_tutores = new ArrayList<>();

        while (cursor.moveToNext()) {
            String email= cursor.getString(COLUNA_EMAIL);
            String telefone= cursor.getString(COLUNA_TELEFONE);
            TelTutor telTutor = new TelTutor(email,telefone);
            listaTel_tutores.add(telTutor);

        }

        return listaTel_tutores;
    }
    public TelTutor listarTel_tutor(String email_verifica){
        Cursor cursor;
        TelTutor telTutor = new TelTutor();
        cursor = db.rawQuery("SELECT * FROM telTutor WHERE email="+email_verifica, null);

        while (cursor.moveToNext()) {
            String email = cursor.getString(COLUNA_EMAIL);
            String telefone = cursor.getString(COLUNA_TELEFONE);
            telTutor.setEmail(email);
            telTutor.setTelefone(telefone);
        }
        return telTutor;
    }

    //imgpet
    public long inserirImg_pet(ImgPet imgPet){
        ContentValues valores;
        valores = new ContentValues();
        valores.put("id", imgPet.getId());
        valores.put("telefone", imgPet.getFoto());
        long resultado = db.insert("imgPet", null, valores);
        return resultado;
    }
    public int deletarImg_pet(ImgPet imgPet){
        String where = "id =" + imgPet.getId();
        return db.delete("imgPet", where,null);
    }

    public int alterarImg_pet(ImgPet imgPet){
        ContentValues valores;
        valores = new ContentValues();
        String where = "id ="+imgPet.getId();
        valores.put("foto",imgPet.getFoto());
        int resultado = db.update("imgPet",valores,where,null);
        return resultado;
    }

    public ArrayList<ImgPet> listarImg_pet(){
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM imgPet", null);
        ArrayList<ImgPet> listaImg_pet = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id= Integer.parseInt(cursor.getString(COLUNA_ID));
            String foto= cursor.getString(COLUNA_IMG);
            ImgPet imgPet = new ImgPet(id,foto);
            listaImg_pet.add(imgPet);
        }

        return listaImg_pet;
    }
    public ImgPet listariImg_pet(int id_verifica){
        Cursor cursor;
        ImgPet imgPet = new ImgPet();
        cursor = db.rawQuery("SELECT * FROM imgPet WHERE id="+id_verifica, null);

        while (cursor.moveToNext()) {
            int id= Integer.parseInt(cursor.getString(COLUNA_ID));
            String foto= cursor.getString(COLUNA_IMG);
            imgPet.setId(id);
            imgPet.setFoto(foto);

        }
        return imgPet;
    }

    public SQLiteDatabase getConnection() {
        return db;
    }


}





