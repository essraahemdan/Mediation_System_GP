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

        String upservername = "upstream_server";
        ServerData upserverd = new ServerData();
        upserverd = databaseHandler.getServerData(upservername);
        String uppath = databaseHandler.getCDRPath(upservername);

        String downservername = "downstream_server";
        ServerData downserverd = new ServerData();
        downserverd = databaseHandler.getServerData(downservername);
        String downpath = databaseHandler.getCDRPath(downservername);

        downFile(upserverd.getServerIP(), upserverd.getServerPort(), upserverd.getServerUserName(), upserverd.getServerPassword(), uppath, "upstream_server1_New_CDR.ber", LOCAL_ASN_ARCHIVE_PATH);
        FileInputStream in = new FileInputStream(new File("C:\\Users\\Essraa\\Documents\\NetBeansProjects\\MediationSystem\\src\\XMLcdrs\\"));
        uploadFile(downserverd.getServerIP(), downserverd.getServerPort(), downserverd.getServerUserName(), downserverd.getServerPassword(), downpath, "upstream_server1_New_CDR.ber", in);
    }

    public static boolean downFile(String url, int port, String username, String password, String remotePath, String fileName, String localPath) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    public static boolean uploadFile(String url, int port, String username, String password, String path, String filename, InputStream input) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);//connect to FTP server
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(filename, input);

            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
}
