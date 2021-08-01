import java.io.IOException;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;

public class FirstMQBase {
public static void main(String[] args) {
  MQEnvironment.hostname = "127.0.0.1"; // 接続先ホスト
   MQEnvironment.channel = "CLIENT.QM_ORANGE"; // チャネル名（サーバー接続チャネル）
   MQEnvironment.port = 1415; // リスナーがlisternしているport.
   MQEnvironment.CCSID = 932; // 接続先キュー・マネージャーの
   // CCSID属性の値
   MQEnvironment.properties.put( MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES );
   MQQueueManager myQmgr;
   try {
       myQmgr = new MQQueueManager( "QM_ORANGE" );
       MQQueue myQueue = new MQQueue(
               myQmgr, // MQQueueManagerのインスタンス
               "Q1", // (String) キューの名前
               CMQC.MQOO_OUTPUT | CMQC.MQOO_INPUT_AS_Q_DEF, // (int) オプション
               null, // (String) キュー・マネージャー名
               null, // (String) 動的キューの名前
               null // (String) 代替ユーザーID
               );
       MQMessage msg = new MQMessage(); // ヘッダ部分+ ユーザー・データ
       msg.messageType = MQC.MQMT_DATAGRAM;
       msg.characterSet = 932; // このメッセージに含まれるデータのCCSID
       msg.format = MQC.MQFMT_STRING; // ユーザー・データは、文字型
       msg.persistence = MQC.MQPER_PERSISTENT; // パーシスタント・メッセージ
       String myMsg = "台風14号接近中!\n"; // ユーザー・データ
       msg.writeString( myMsg); // 上記のデータを、先に設定したCCSIDで扱う
       myQueue.put( msg); // キューに書き込む.
       myQueue.get( msg); // メッセージを読み出す
       StringBuffer buf = new StringBuffer(
           msg.readStringOfByteLength(msg.getMessageLength())) ;
       System.out.println(buf);
       myQueue.close(); // キューのクローズ.
       myQmgr.disconnect(); // キュー・マネージャーからの切断
   } catch (MQException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
   } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
   }
}
}