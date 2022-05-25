package crud;

public class User {
    
    private String nomeUser;
    private String senha;

    public User(String nomeUser, String senha){
        this.nomeUser = nomeUser;
        this.senha = senha;
    }

    public User(String senha){
        this.senha=senha;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
