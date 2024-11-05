package src.Unidad1.repasoExamen.ficheros;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xml {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException, XPathExpressionException {
        Document doc = crearDOM();
        estructurarDOM(doc);
        DOMtoXML(doc);

        Element raiz = doc.getDocumentElement();
        //recorrerXML(raiz, 0);
        
        
        XPath xpath = XPathFactory.newInstance().newXPath();
        
        String xpathExpression = "//libro[./titulo = 'Don Quijote de la Mancha']";
        
        NodeList lista = (NodeList) xpath.evaluate(xpathExpression, doc, XPathConstants.NODESET);
        
        for(int i = 0; i<lista.getLength(); i++){
            System.out.println(lista.item(i).getAttributes().item(0));
        }
    }

    public static Document crearDOM() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        return doc;
    }

    public static void DOMtoXML(Document doc) throws TransformerConfigurationException, TransformerException {
        File archivoXML = new File("C:\\Users\\Fernando\\Desktop\\d\\libreria.xml");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(archivoXML);
        transformer.transform(source, result);
    }

    public static void estructurarDOM(Document doc) throws TransformerConfigurationException, TransformerException {

        Element libreria = doc.createElement("libreria");
        doc.appendChild(libreria);

        Element nombre = doc.createElement("nombre");
        libreria.appendChild(nombre);
        nombre.setTextContent("Libreria Pepe");

        Element libros = doc.createElement("libros");
        libreria.appendChild(libros);

        Element libro1 = doc.createElement("libro");
        libro1.setAttribute("isbn", "1234567890");

        libros.appendChild(libro1);

        Element titulo1 = doc.createElement("titulo");
        titulo1.setTextContent("Don Quijote de la Mancha");
        libro1.appendChild(titulo1);

        Element autor1 = doc.createElement("autor");
        autor1.setTextContent("Miguel de Cervantes");
        libro1.appendChild(autor1);

        Element libro2 = doc.createElement("libro");
        libro2.setAttribute("isbn", "234567901");
        libros.appendChild(libro2);

        Element titulo2 = doc.createElement("titulo");
        titulo2.setTextContent("El Lazarillo de Tormes");
        libro2.appendChild(titulo2);

        Element autor2 = doc.createElement("autor");
        autor2.setTextContent("Anonimo");
        libro2.appendChild(autor2);

        Element libro3 = doc.createElement("libro");
        libro3.setAttribute("isbn", "45678910123");
        libros.appendChild(libro3);

        Element titulo3 = doc.createElement("titulo");
        titulo3.setTextContent("La vida es sueno");
        libro3.appendChild(titulo3);

        Element autor3 = doc.createElement("autor");
        autor3.setTextContent("Pedro Calderon de la Barca");
        libro3.appendChild(autor3);

    }

    public static void recorrerXML(Node nodo, int contador) {

        for (int i = 0; i < contador; i++) {
            System.out.print(" ");
        }

        switch (nodo.getNodeType()) {
            case Node.ELEMENT_NODE:

                System.out.print("<" + nodo.getNodeName());
                if (nodo.hasAttributes()) {
                    NamedNodeMap atributos = nodo.getAttributes();
                    for (int i = 0; i < atributos.getLength(); i++) {
                        System.out.print(" " + atributos.item(i).getNodeName() + "=" + atributos.item(i).getNodeValue());
                    }
                }

                System.out.println(">");

                NodeList hijos = nodo.getChildNodes();
                for (int i = 0; i < hijos.getLength(); i++) {
                    recorrerXML(hijos.item(i), contador + 1);
                }

                for (int i = 0; i < contador; i++) {
                    System.out.print(" ");
                }

                System.out.println("</" + nodo.getNodeName() + ">");
                break;

            case Node.TEXT_NODE:
                System.out.println(nodo.getNodeValue());

        }

    }

}
