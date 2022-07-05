package com.iti.mediation;

import com.iti.servercon.MediationFTPClient;
import database.DatabaseCon;
import database.DatabaseHandler;
import database.ServerData;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import sun.security.pkcs11.Secmod;

/**
 *
 * @author Essraa
 */
public class Mediationnode {
    
    private static final String LOCAL_ASN_ARCHIVE_PATH = "D:\\GP\\Archive_ASN";
    private static final String LOCAL_CSV_ARCHIVE_PATH = "D:\\GP\\Archive_CSV";
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        PreparedStatement pst = null;
        ResultSet result = null;
        DatabaseCon db = DatabaseCon.getDatabaseInstance();
        db.connect();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        MediationFTPClient fTPClient = new MediationFTPClient();
        String sourcePath = "src\\ASNcdrs\\upstream_server1_New_CDR.ber";
        String destPath = "D:\\Mediation_System_GP\\MediationSystem\\src\\XMLcdrs\\cdr1.xml";
        
        ChilkatExample ce = new ChilkatExample();
        
        String upservername = "upstream_server";
        ServerData upserverd = new ServerData();
        upserverd = databaseHandler.getServerData(upservername);
        String uppath = databaseHandler.getCDRPath(upservername);
        
        String downservername = "downstream_server";
        ServerData downserverd = new ServerData();
        downserverd = databaseHandler.getServerData(downservername);
        String downpath = databaseHandler.getCDRPath(downservername);
        
        fTPClient.downFile(upserverd.getServerIP(), upserverd.getServerPort(), upserverd.getServerUserName(), upserverd.getServerPassword(), uppath, "upstream_server1_New_CDR.ber", "src\\ASNcdrs");
        ce.asnToXml(sourcePath, destPath);
        FileInputStream in = new FileInputStream(new File(destPath));
        fTPClient.uploadFile(downserverd.getServerIP(), downserverd.getServerPort(), downserverd.getServerUserName(), downserverd.getServerPassword(), downpath, "cdr1.xml", in);
    }
    
}
