package crud;

public class UserEmail extends User {
    
    private String email;

    public UserEmail(String email, String senha){
        super(senha);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
