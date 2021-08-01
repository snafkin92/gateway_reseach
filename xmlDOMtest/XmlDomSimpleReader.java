package xmlDOMtest;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlDomSimpleReader {
    public static void main(String args[]) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();///[1]
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();///[2]
        Document document = documentBuilder.parse(new File("C:\\tmp\\receipt_two_elements.xml"));//[3]

        Node receptNode = document.getDocumentElement();//[4]
        Node itemNodes = receptNode.getFirstChild();//[5]
        while(itemNodes != null) {//[6]
            Node textNode = itemNodes.getFirstChild();//[7]
            if (checkTextNode(textNode)) {//[8]
                System.out.println("[9] テキストノード = " + textNode.getNodeValue());
            }
            itemNodes = itemNodes.getNextSibling();//[10]
        }
    }
    private static boolean checkTextNode(Node node) {//[20]
        if (node == null)   return false;//[21]
        if (node.getNodeType() == Node.TEXT_NODE)//[22]
            return true;//[23]
        return false;//[24]
    }
}
