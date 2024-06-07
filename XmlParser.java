import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.Dictionary;
import java.util.Hashtable;

public class XmlParser {
    Dimension d;
    Dictionary<String, Voisinage> gk;
    Operateur op;
    Execution e;
    public XmlParser(String path) {
        try {
            // Parse the XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(path); // Provide the path to your XML file
            doc.getDocumentElement().normalize();

            // Read Dimension
            Element dimensionElement = (Element) doc.getElementsByTagName("Dimension").item(0);
            int dimension = Integer.parseInt(dimensionElement.getTextContent());

            Element dimensionSizeElement = (Element) doc.getElementsByTagName("DimensionSize").item(0);
            String[] sizeParts = dimensionSizeElement.getTextContent().replaceAll("[{}]", "").split(",");
            int[] sizes = new int[sizeParts.length];
            for (int i = 0; i < sizeParts.length; i++) {
                sizes[i] = Integer.parseInt(sizeParts[i]);
            }

            d = new Dimension(dimension, 0, sizes);
            System.out.println("Dimension created");
           

            // Read Voisinage
    
            gk = new Hashtable<>();

            NodeList voisinageList = doc.getElementsByTagName("Voisinage").item(0).getChildNodes();
            for (int i = 0; i < voisinageList.getLength(); i++) {
                if (voisinageList.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element voisinageElement = (Element) voisinageList.item(i);
                    String name = voisinageElement.getTagName();
                    String scoord = voisinageElement.getTextContent(); 
                    String[] coords = scoord.substring(1, scoord.length() - 1).split("\\},\\{");
                    int[][] voisinageArray = new int[coords.length][];

                    for (int j = 0; j < coords.length; j++) {
                        coords[j] = coords[j].replaceAll("[{}]", ""); // Remove any remaining braces
                        String[] point = coords[j].split(",");
                        int[] intPoint = new int[point.length];
                        for (int k = 0; k < point.length; k++) {
                            intPoint[k] = Integer.parseInt(point[k]);
                        }
                        voisinageArray[j] = intPoint;
                    }

                    Voisinage v = new Voisinage(voisinageArray.length, voisinageArray[0].length, voisinageArray);
                    gk.put(name, v);
                }
            }
            System.out.println("Voisinage created");

            // Read Regle
            Element regleElement = (Element) doc.getElementsByTagName("Regle").item(0);
            String regle = regleElement.getTextContent();
            op = Operateur.buildTree(regle, 0, gk);
            System.out.println("Arbre created");

            // Initialize Execution
            e = new Execution(op, d);

            // Read Initialisation
            Element initialisationElement = (Element) doc.getElementsByTagName("Initialisation").item(0);
            String initContent = initialisationElement.getTextContent();
            String[] initParts = initContent.substring(1, initContent.length() - 1).split("\\},\\{");
            System.out.println(initParts[0]);
            for (String part : initParts) {
                part = part.replaceAll("[{}]", ""); // Remove any remaining braces
                String[] coord = part.split(",");
                int[] intCoord = new int[coord.length];
                for (int i = 0; i < coord.length; i++) {
                    intCoord[i] = Integer.parseInt(coord[i]);
                }
                // Assuming d.set method can handle variable length coordinates
                d.set(1, intCoord);
            }
            System.out.println("Initialisation completed");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Dimension getDimension() {
        return d;
    }

    public Dictionary<String, Voisinage> getVoisinages() {
        return gk;
    }

    public Operateur getOperateur() {
        return op;
    }

    public Execution getExecution() {
        return e;
    }
    
    



}
