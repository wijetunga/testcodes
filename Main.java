import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

class XMLParserExample {

    public static void main(String[] args) throws Exception {
        // Create a DocumentBuilder object
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML file
        File xmlFile = new File("data.xml");
        Document document = builder.parse(xmlFile);

        // Get the root element
        Element rootElement = document.getDocumentElement();

        // Get the filepath element
        String filepath = rootElement.getElementsByTagName("filepath").item(0).getTextContent();

        // Get the cities element and create a map to store the city data
        Map<String, Map<String, String>> citiesMap = new HashMap<>();
        NodeList cityNodes = rootElement.getElementsByTagName("city");
        for (int i = 0; i < cityNodes.getLength(); i++) {
            Element cityElement = (Element) cityNodes.item(i);
            String cityName = cityElement.getAttribute("name");
            Map<String, String> cityData = new HashMap<>();
            cityData.put("population", cityElement.getAttribute("population"));
            cityData.put("distance", cityElement.getAttribute("distance"));
            cityData.put("age", cityElement.getAttribute("age"));
            citiesMap.put(cityName, cityData);
        }

        // Print the data
        System.out.println("Filepath: " + filepath);
        System.out.println("Cities:");
        for (Map.Entry<String, Map<String, String>> entry : citiesMap.entrySet()) {
            String cityName = entry.getKey();
            Map<String, String> cityData = entry.getValue();
            System.out.println(cityName + ":");
            System.out.println("\tPopulation: " + cityData.get("population"));
            System.out.println("\tDistance: " + cityData.get("distance"));
            System.out.println("\tAge: " + cityData.get("age"));
        }
    }
}
