package javaxml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xpath {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        //Crea un DocumentBuilderFactory y un árbol DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(new File("personas.xml"));

        //Crea el objeto XpathFactory
        XPath xpath = XPathFactory.newInstance().newXPath();

        //Crea un XpathExpression con la consulta deseada
        String xpathExpression = "//persona[./edad>30]";

        //Consultas
        NodeList nodos = (NodeList) xpath.evaluate(xpathExpression, documento, XPathConstants.NODESET);
        int contador = 0;

        for (int i = 0; i < nodos.getLength(); i++) {
            mostrarPersona(nodos.item(i), contador);
        }
    }

    public static void mostrarPersona(Node nodo, int contador) {

        for (int i = 0; i < contador; i++) {
            System.out.print("\t");
        }

        switch (nodo.getNodeType()) {

            case Node.ELEMENT_NODE:
                if (!nodo.hasAttributes()) {

                    System.out.println("<" + nodo.getNodeName() + ">");
                    NodeList hijos = nodo.getChildNodes();

                    for (int i = 0; i < hijos.getLength(); i++) {
                        mostrarPersona(hijos.item(i), contador + 1);
                    }

                    for (int i = 0; i < contador; i++) {
                        System.out.print("\t");
                    }
                    System.out.println("</" + nodo.getNodeName() + ">");
                } else {
                    System.out.print("<" + nodo.getNodeName());
                    NamedNodeMap nnm = nodo.getAttributes();
                    for (int i = 0; i < nnm.getLength(); i++) {
                        System.out.print(" " + nnm.item(i).getNodeName() + "=" + nnm.item(i).getNodeValue());
                    }
                    System.out.println(">");

                    NodeList hijos = nodo.getChildNodes();

                    for (int i = 0; i < hijos.getLength(); i++) {
                        mostrarPersona(hijos.item(i), contador + 1);
                    }

                    for (int i = 0; i < contador; i++) {
                        System.out.print("\t");
                    }
                    System.out.println("</" + nodo.getNodeName() + ">");
                }

                break;

            case Node.TEXT_NODE:

                System.out.println(nodo.getTextContent().trim());
                break;
        }
    }

}
