import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Vector;
import java.awt.BorderLayout;
public class Interface extends JPanel
{
	private int largeur, hauteur, taille_case;
	private boolean play = true;
	private int[][] casesAColorier;
	private int viewX = 0; // Coordonnée x de la vue
	private int viewY = 0; // Coordonnée y de la vue
	private static final int VIEW_SIZE = 100; // Taille de la vue
	private float gradient = 0.0f;
	private Vector<String> xmlFileNames = new Vector<>();
	private String path = null;
	private JScrollPane scrollPane;	
	JFrame window;

	public Interface(int largeurs, int hauteurs, int taille_case)
	{
		this.largeur = 100;
		this.hauteur = 100;
		this.taille_case = taille_case;
		casesAColorier = new int[VIEW_SIZE][VIEW_SIZE];
		JButton monBouton = new JButton("STOP");
		monBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play = !play;
				refocus();
			}
		});




		window = new JFrame();

		window.setSize(largeur*taille_case + 25, hauteur*taille_case + 25);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.getHSBColor(0,0f, 0.140f));
		window.setFocusable(true);
		window.requestFocusInWindow();
		window.setLayout(new BorderLayout());
		window.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
					case KeyEvent.VK_UP:
                    	--viewY; // Déplace la vue vers le haut
                    	break;
                	case KeyEvent.VK_DOWN:
                    	++viewY; // Déplace la vue vers le bas
                    	break;
                	case KeyEvent.VK_LEFT:
                    	--viewX; // Déplace la vue vers la gauche
                   		break;
                	case KeyEvent.VK_RIGHT:
                    	++viewX; // Déplace la vue vers la droite
                    	break;
				}
			}
		});
		
		

		loadXmlFileNames();
        JList<String> fileList = new JList<>(xmlFileNames);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fileList.setForeground(Color.WHITE);
		fileList.setBackground(Color.getHSBColor(0,0f, 0.140f));
        fileList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedFile = fileList.getSelectedValue();
                System.out.println("Fichier sélectionné : " + selectedFile);
                path = selectedFile;

            }
        });
		
        scrollPane = new JScrollPane(fileList);
		window.validate();
        window.repaint();
        window.add(scrollPane, BorderLayout.CENTER);
		window.setVisible(true);
		window.add(this);
		window.add(monBouton, "South");
		window.setVisible(true);
	}


	public void refocus(){
		window.requestFocusInWindow();
	}

	public String getPath(){
		if(path == null) return null;
		window.remove(scrollPane);
		window.setLayout(new BorderLayout());
		window.validate();
        window.repaint();
		window.requestFocusInWindow();
		return "FichierXML/" + path;
	}



	public boolean getPlay(){
		return play;
	}
	public int getViewX() {
		return viewX;
	}
	public int getViewY() {
		return viewY;
	}

	@Override
	//Fonction d'affichage de la grille.
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for (int i = 0; i < VIEW_SIZE; i++)
		{
			for (int j = 0; j < VIEW_SIZE; j++)
			{
				float h = (float)(i * j) / ((VIEW_SIZE - 1) * (VIEW_SIZE - 1)) /3;
				g.setColor(Color.getHSBColor(h+gradient+0.5f, 0.5f, 1.0f));
				if (casesAColorier[i][j] == 1)
				{
					g.fillRect(taille_case + (i * taille_case), taille_case + (j * taille_case), taille_case, taille_case);
				}
			}
		}
		gradient += 0.01f;
		g.setColor(Color.BLACK);
		g.drawRect(taille_case, taille_case, VIEW_SIZE*taille_case,VIEW_SIZE*hauteur*taille_case);
		for (int i = 0; i <= VIEW_SIZE*taille_case; i += taille_case) {
			g.drawLine(i, taille_case, i, VIEW_SIZE*taille_case+taille_case);
		}
		for (int i = 0; i <= VIEW_SIZE*taille_case; i += taille_case) {
			g.drawLine(taille_case, i, VIEW_SIZE*taille_case+taille_case, i);
		}
	}
	public void colorierCase(int x, int y)
	{
		casesAColorier[x][y] = 1;
	}
    public void tuerCase(int x, int y)
	{
		casesAColorier[x][y] = 0;
    }
	public int getLargeur()
	{
		return largeur;
	}
	public int getHauteur()
	{
		return hauteur;
	}
	private void loadXmlFileNames() {
        File folder = new File("FichierXML");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    xmlFileNames.add(file.getName());
					System.out.println(file.getName());
                }
            }
        }
		else{
			System.out.println("Pas de fichier xml");
		}
    }
}


