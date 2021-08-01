public class FtpSample {

    public static void main(String[] args){
        System.out.print("start: FtpSample\r\n");
        FtpMng ftpClient = new FtpMng();

        // １：接続
        ftpClient.connect();

        // ２：ログイン
        ftpClient.login();

        // ３：ダウンロード
        //ftpClient.downLoad();

        // ４：アップロード
        ftpClient.upLoad();

        // ５：切断
        ftpClient.disConnect();

        System.out.print("end: FtpSample\r\n");
    }
}