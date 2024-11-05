package src.javaxml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class prueba {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File archivo = new File("recetas.xml");
        Document doc = XMLtoDOM(archivo);

        Element raiz = doc.getDocumentElement();
        int contador = 0;

        recorrerXML2(doc, raiz, contador);
    }

    public static Document XMLtoDOM(File archivo) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(archivo);

        return doc;
    }

    public static void recorrerXML(Document doc, Node nodo, int contador) {
        for (int i = 0; i < contador; i++) {
            System.out.print(" ");
        }

        switch (nodo.getNodeType()) {
            case Node.ELEMENT_NODE:
                if (!nodo.hasAttributes()) {
                    System.out.print("<" + nodo.getNodeName());
                } else {
                    NamedNodeMap atributos = nodo.getAttributes();
                    System.out.print("<" + nodo.getNodeName());

                    for (int i = 0; i < atributos.getLength(); i++) {
                        System.out.print(" " + atributos.item(i).getNodeName() + "=" + atributos.item(i).getNodeValue());
                    }
                }

                System.out.println(">");
                NodeList hijos = nodo.getChildNodes();

                for (int i = 0; i < hijos.getLength(); i++) {
                    recorrerXML(doc, hijos.item(i), contador + 1);
                }

                for (int i = 0; i < contador; i++) {
                    System.out.print(" ");
                }
                
                System.out.println("</" + nodo.getNodeName() + ">");

                break;

            case Node.TEXT_NODE:
                Text t = (Text) nodo;
                if (!nodo.getTextContent().contains("\n")) {
                    
                    for(int i = 0; i<contador; i++){
                        System.out.print(" ");
                    }
                    System.out.println(t.getTextContent());

                }
        }
    }
    
    public static void recorrerXML2(Document doc, Node nodo, int contador) {
        
        for(int i = 0; i<contador; i++){
                    System.out.print(" ");
        }
        
        switch(nodo.getNodeType()){
            case Node.ELEMENT_NODE:
                
                if(!nodo.hasAttributes()){
                    System.out.print("<"+nodo.getNodeName());
                }else{
                    NamedNodeMap atributos = nodo.getAttributes();
                    System.out.print("<"+nodo.getNodeName());
                    for(int i = 0; i<atributos.getLength(); i++){
                        System.out.print(" "+atributos.item(i).getNodeName()+"="+atributos.item(i).getNodeValue());
                    }
                }
                
                System.out.println(">");
                NodeList hijos = nodo.getChildNodes();
                
                for(int i = 0; i<hijos.getLength(); i++){
                    recorrerXML(doc, hijos.item(i), contador + 1); 
                }
                
                for(int i = 0; i<contador; i++){
                    System.out.print(" ");
                }
                
                System.out.println("</"+nodo.getNodeName()+">");
            break;
            
            case Node.TEXT_NODE:
                Text t = (Text) nodo;
                System.out.println(t.getTextContent());
                
            break;
        }
    }

}
