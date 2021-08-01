import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpMng {

    private FTPClient   cli;

    // コンストラクタ
    public FtpMng() {
    }

    // 接続
    public boolean connect() {
        int rep;

        System.out.print("start: connect\r\n");
        try {
            cli = new FTPClient();
            cli.setDataTimeout(60000);
            cli.connect("localhost",60021);
            rep = cli.getReplyCode();
            if (!FTPReply.isPositiveCompletion(rep)) {
                return false;
            } 
            System.out.print("end: connect\r\n");
            return true;
        } catch (SocketException e) {
        	 System.out.print("error: connect socket\r\n");
            return false;
        } catch (IOException ie) {
        	 System.out.print("id error: connect\r\n");
            return false;
        }
    }

    // ログイン
    public boolean login() {
        System.out.print("start: login\r\n");
        try {
            if (!cli.login("ftpuser", "ftpuser")) {
                return false;
            }
            System.out.print("end: login\r\n");
            return true;
        } catch (IOException ie) {
        	System.out.print("error: login io\r\n");
            return false;
        }
    }

    // ダウンロード
    public void downLoad() {
        FileOutputStream    outputstream;
        boolean             isRetrieve;

        System.out.print("start: downLoad\r\n");
        try {
            outputstream =
                new FileOutputStream("aaa.txt");
            isRetrieve =
               cli.retrieveFile("aaa.txt", outputstream);
            outputstream.close();
            if (!isRetrieve) {
                System.out.print("error: downLoad\r\n");
            }
            System.out.print("end: downLoad\r\n");
            return;
        } catch (IOException ie) { 
            return;
        }
    }

    // アップロード
    public void upLoad() {
        FileInputStream     inputstream;
        boolean             isStore;

        System.out.print("start: upLoad\r\n");
        try {
            inputstream =
                new FileInputStream("C:\\tmp\\upload.txt");
            isStore =
                cli.storeFile("uploads.txt",
                inputstream);
            inputstream.close();
            if (!isStore) {
                System.out.print("error: upLoad\r\n");
            } 
            System.out.print("end: upLoad\r\n");
            return;
        } catch (IOException ie) {
        	 System.out.print("ie error: upLoad\r\n");
            return;
        }
    }

    // 切断
    public boolean disConnect() {

        System.out.print("start: disConnect\r\n");
        try {
            if (cli != null && cli.isConnected()) {
                cli.disconnect(); 
            }
            System.out.print("end: disConnect\r\n");
            return true;
        } catch (IOException ie) {
            return false;
        }
    }
}