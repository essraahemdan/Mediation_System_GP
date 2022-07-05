package database;

public class ServerData {
    public String name;
    public String description;
    public String ip;
    public int port;
    public String protocol;
    public String username;
    public String password;
    public int server_id;
    public  ServerData()
    {
        
        name="";
        description = "";
        ip="";
        port=0;
        protocol="";
        username="";
        password="";
        server_id =0 ;
    }
    public  ServerData( String nam, String desc, String nip ,int nport,String prot , String uname,String pass ,int sid )
    {
        server_id =sid ;
        name=nam;
        description = desc;
        ip=nip;
        port=nport;
        protocol=prot;
        username=uname;
        password=pass;
    }
}