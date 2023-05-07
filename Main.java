import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

class XMLParserExample {

    public static void main(String[] args) throws Exception {
        // Create a DocumentBuilder object
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML file
        File xmlFile = new File("data.xml");
        Document document = builder.parse(xmlFile);

        // Get the changefield element and create a map to store the change data
        Map<String, Map<String, String>> changeData = new HashMap<>();
        Element rootElement = document.getDocumentElement();
        NodeList changeNodes = rootElement.getElementsByTagName("changethisfield");
        for (int i = 0; i < changeNodes.getLength(); i++) {
            Element changeElement = (Element) changeNodes.item(i);
            String fieldName = changeElement.getAttribute("name");
            Map<String, String> fieldData = new HashMap<>();
            fieldData.put("source", changeElement.getAttribute("source"));
            fieldData.put("startat", changeElement.getAttribute("startat"));
            fieldData.put("endat", changeElement.getAttribute("endat"));
            fieldData.put("length", changeElement.getAttribute("length"));
            changeData.put(fieldName, fieldData);
        }

        // Print the data
        System.out.println("Change Data:");
        for (Map.Entry<String, Map<String, String>> entry : changeData.entrySet()) {
            String fieldName = entry.getKey();
            Map<String, String> fieldData = entry.getValue();
            System.out.println(fieldName + ":");
            System.out.println("\tSource: " + fieldData.get("source"));
            System.out.println("\tStart At: " + fieldData.get("startat"));
            System.out.println("\tEnd At: " + fieldData.get("endat"));
            System.out.println("\tLength: " + fieldData.get("length"));
        }
    }
}
