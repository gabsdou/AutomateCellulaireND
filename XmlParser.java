import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

public class XmlParser {
    Dimension d;
    Dictionary<String, Voisinage> gk;
    Operateur op;
    Execution e;
    int[] coupe;  // Nouvelle variable pour stocker le tableau coupe
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

              // Read Coupe
              Element coupeElement = (Element) doc.getElementsByTagName("Coupe").item(0);
              String[] coupeParts = coupeElement.getTextContent().split(",");
              coupe = new int[coupeParts.length];
              for (int i = 0; i < coupeParts.length; i++) {
                    coupeParts[i] = coupeParts[i].replaceAll("[{}]", ""); // Remove any remaining braces
                  if (coupeParts[i].equals(":")) {
                      coupe[i] = -1;
                  } else {
                      coupe[i] = Integer.parseInt(coupeParts[i]);
                  }
              }
              System.out.println("Coupe created");
           

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
            if (!initContent.trim().isEmpty()) {
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
            } else {
                System.out.println("Initialisation is empty");
            }
            System.out.println("Initialisation completed");

            // Read InitialisationRandom
            Element initialisationRandomElement = (Element) doc.getElementsByTagName("InitialisationRandom").item(0);
            if (initialisationRandomElement != null) {
                int percentage = Integer.parseInt(initialisationRandomElement.getTextContent());
                initializeRandomCells(percentage);
                System.out.println("InitialisationRandom completed with " + percentage + "% cells set.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initializeRandomCells(int percentage) {
        Random random = new Random();
        int totalCells = 1;
        ArrayList<Integer> sizes = new ArrayList<Integer>();
        Dimension.getDimSize(d, sizes);
        for (int size : sizes) {
            totalCells *= size;
        }

        int cellsToSet = totalCells * percentage / 100;
        for (int i = 0; i < cellsToSet; i++) {
            int[] coords = new int[sizes.size()];
            for (int j = 0; j < coords.length; j++) {
                coords[j] = random.nextInt(sizes.get(j));
            }
            d.set(1, coords);
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

    public int[] getCoupe() {
        return coupe;
    }
    
    



}
