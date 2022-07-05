
package com.iti.servercon;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class MediationFTPClient extends FTP {

    private FTPClient ftpClient;

    public int connectToServer(String ip, int port, String userName, String password) {

        ftpClient = new FTPClient();
        int validConnection = 0;

        try {
            ftpClient.connect(ip, port);
            ftpClient.login(userName, password);
            validConnection = 1;
        } catch (IOException ex) {
            Logger.getLogger(MediationFTPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validConnection;
    }

    public void disconnectFromServer() {
        try {
            ftpClient.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(MediationFTPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

