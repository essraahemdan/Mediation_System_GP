package database;

public class Users {

    private String uname;
    //public  String email;
    private String password;

    public Users() {

        uname = "";
        //email = "";
        password = "";

    }

    public Users(String u, String p) {

        uname = u;
        //email = e;
        password = p;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
