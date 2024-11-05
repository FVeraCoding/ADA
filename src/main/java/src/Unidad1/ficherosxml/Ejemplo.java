package src.Unidad1.ficherosxml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Ejemplo {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        String ruta = "C:\\users\\Fernando\\Desktop\\personas.xml";
        File fichero = new File(ruta);

        Document doc = xmlToDOM(fichero);
        Node root = doc.getFirstChild();
        System.out.println("");

        listarNodos(root, 0);

    }

    public static Document xmlToDOM(File fichero) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(fichero);

        return doc;
    }

    public static void DOMtoXML(Document doc) throws TransformerException {
        File archivoXML = new File("C:\\Users\\Fernando\\Desktop\\personas.xml");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(archivoXML);

        transformer.transform(source, result);

    }

    public static void listarNodos(Node node, int contador) {

        if (contador > 0) {
            for (int i = 0; i < contador; i++) {
                System.out.print("\t");
            }
        }

        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                System.out.println("<" + node.getNodeName() + ">");
                NodeList hijos = node.getChildNodes();
                for (int i = 0; i < hijos.getLength(); i++) {
                    Node hijo = hijos.item(i);
                    listarNodos(hijo, contador + 1);
                }

                if (contador > 0) {
                    for (int i = 0; i < contador; i++) {
                        System.out.print("\t");
                    }
                }
                
                System.out.println("</" + node.getNodeName() + ">");
                break;

            case Node.TEXT_NODE:
                Text t = (Text) node;
                System.out.println(t.getWholeText().trim());
                break;
        }
    }

}
