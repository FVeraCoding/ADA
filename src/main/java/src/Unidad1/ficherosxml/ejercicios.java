package src.Unidad1.ficherosxml;

import java.io.File;
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
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class ejercicios {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        //Ejercicio1
        Document doc = crearDOM();
        
        //Ejercicio 3
        DOMtoXML(doc);
        
        //Ejercicio 2.1
        Element raiz = doc.getDocumentElement();
        int contador = 0;
        
        
        //Ejercicio4
        Element libro4 = doc.createElement("libro");
        libro4.setAttribute("isbn", "8901234567");
        
        Element tituloLibro4 = doc.createElement("titulo");
        Text textoTitulo4 = doc.createTextNode("100 anios de soledad");
        Element autorLibro4 = doc.createElement("autor");
        Text textoAutor4 = doc.createTextNode("Gabriel Garcia Marquez");
        tituloLibro4.appendChild(textoTitulo4);
        autorLibro4.appendChild(textoAutor4);
        
        libro4.appendChild(tituloLibro4);
        libro4.appendChild(autorLibro4);
        
        Node libros = doc.getElementsByTagName("libros").item(0);
        libros.appendChild(libro4);

        //Ejercicio 5
        Node direccion = doc.createElement("direccion");
        Text textoDireccion = doc.createTextNode("C/ Amiel 12");
        direccion.appendChild(textoDireccion);
        
        raiz.insertBefore(direccion, libros);
        
        // Ejercicio6
        Node libro2 = libros.getChildNodes().item(1);
        libros.removeChild(libro2);
        
        // Ejercicio7
        direccion.setTextContent("C/ Amiel 26");
        
        // Ejercicio 8
        
        NodeList librosHijos = libros.getChildNodes();
        for(int i = 0; i<librosHijos.getLength(); i++){
            Element hijo = (Element) librosHijos.item(i);
            hijo.setAttribute("estado", "disponible");
        }
        
        // Ejercicio 9
        

        Element primerLibro = (Element) librosHijos.item(0);
        primerLibro.removeAttribute("estado");
        
        Element tercerLibro = (Element) librosHijos.item(2);
        tercerLibro.removeAttribute("estado");

        // Ejercicio2.2
        recorrerXML(doc, raiz, contador);
        
    }
    
    

    public static void recorrerXML(Document doc, Node nodo, int contador) {
        
        for(int i = 0; i<contador; i++){
                    System.out.print("\t");
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
                    System.out.print("\t");
                }
                
                System.out.println("</"+nodo.getNodeName()+">");
            break;
            
            case Node.TEXT_NODE:
                Text t = (Text) nodo;
                System.out.println(t.getTextContent());
                
            break;
        }
    }

    public static void DOMtoXML(Document doc) throws TransformerException {
        String ruta = "C:\\Users\\Fernando\\Desktop";
        File archivoXML = new File(ruta, "archivo.xml");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(archivoXML);

        transformer.transform(source, result);
    }

    public static Document crearDOM() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element libreria = doc.createElement("libreria");
        doc.appendChild(libreria);

        Element nombre = doc.createElement("nombre");
        libreria.appendChild(nombre);

        Text nombreLibreria = doc.createTextNode("Libreria Pepe");
        nombre.appendChild(nombreLibreria);

        Element libros = doc.createElement("libros");
        libreria.appendChild(libros);

        Element libro1 = doc.createElement("libro");
        libro1.setAttribute("isbn", "1234567890");

        Element titulo1 = doc.createElement("titulo");
        Text textoTitulo1 = doc.createTextNode("Don Quijote de la Mancha");
        titulo1.appendChild(textoTitulo1);

        Element autor1 = doc.createElement("autor");
        Text textoAutor1 = doc.createTextNode("Miguel de Cervantes");
        autor1.appendChild(textoAutor1);

        libro1.appendChild(titulo1);
        libro1.appendChild(autor1);

        libros.appendChild(libro1);

        Element libro2 = doc.createElement("libro");
        libro2.setAttribute("isbn", "2345678901");

        Element titulo2 = doc.createElement("titulo");
        Text textoTitulo2 = doc.createTextNode("Lazarillo de Tormes");
        titulo2.appendChild(textoTitulo2);

        Element autor2 = doc.createElement("autor");
        Text textoAutor2 = doc.createTextNode("Anonimo");
        autor2.appendChild(textoAutor2);

        libro2.appendChild(titulo2);
        libro2.appendChild(autor2);

        libros.appendChild(libro2);

        Element libro3 = doc.createElement("libro");
        libro3.setAttribute("isbn", "45678910123");

        Element titulo3 = doc.createElement("titulo");
        Text textoTitulo3 = doc.createTextNode("La vida es sueno");
        titulo3.appendChild(textoTitulo3);

        Element autor3 = doc.createElement("autor");
        Text textoAutor3 = doc.createTextNode("Pedro Calderon de la Barca");
        autor3.appendChild(textoAutor3);

        libro3.appendChild(titulo3);
        libro3.appendChild(autor3);

        libros.appendChild(libro3);

        return doc;
    }

}
