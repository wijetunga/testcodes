import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
    public static void main(String[] args) {

        try {
            // Load XML file
            File inputFile = new File("input.xml");

            // Create a document builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file into a Document object
            Document doc = builder.parse(inputFile);

            // Get the root element of the XML file
            Element root = doc.getDocumentElement();

            // Get the child elements of the root element
            NodeList children = root.getChildNodes();

            // Initialize maps to hold data
            Map<String, String> dataMap = new HashMap<>();
            Map<String, Map<String, String>> cityMap = new HashMap<>();

            // Loop through the child elements of the root element
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);

                // Check if the child element is an element node
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) child;

                    // Check if the element is a "filepath" element
                    if (element.getTagName().equals("filepath")) {
                        // Get the text content of the element
                        String filepath = element.getTextContent();
                        dataMap.put("filepath", filepath);
                    }

                    // Check if the element is a "cities" element
                    if (element.getTagName().equals("cities")) {
                        // Get the child elements of the "cities" element
                        NodeList cityChildren = element.getChildNodes();

                        // Loop through the child elements of the "cities" element
                        for (int j = 0; j < cityChildren.getLength(); j++) {
                            Node cityChild = cityChildren.item(j);

                            // Check if the child element is an element node
                            if (cityChild.getNodeType() == Node.ELEMENT_NODE) {
                                Element cityElement = (Element) cityChild;

                                // Get the name of the city
                                String cityName = cityElement.getAttribute("name");

                                // Get the properties of the city
                                NodeList cityPropertyNodes = cityElement.getChildNodes();
                                Map<String, String> cityProperties = new HashMap<>();

                                // Loop through the property nodes of the city
                                for (int k = 0; k < cityPropertyNodes.getLength(); k++) {
                                    Node propertyNode = cityPropertyNodes.item(k);

                                    // Check if the child element is an element node
                                    if (propertyNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element propertyElement = (Element) propertyNode;

                                        // Get the name and value of the property
                                        String propertyName = propertyElement.getTagName();
                                        String propertyValue = propertyElement.getTextContent();

                                        // Add the property to the map
                                        cityProperties.put(propertyName, propertyValue);
                                    }
                                }

                                // Add the city and its properties to the map
                                cityMap.put(cityName, cityProperties);
                            }
                        }
                    }
                }
            }

            // Print the data
            System.out.println("Data:");
            System.out.println(dataMap);

            System.out.println("Cities:");
            for (String cityName : cityMap.keySet()) {
                System.out.println(cityName + ": " + cityMap.get(cityName));
            }

        } catch (Exception e
