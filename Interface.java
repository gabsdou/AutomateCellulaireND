import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Interface extends JPanel
{
	private int largeur, hauteur, taille_case;
	private boolean play = true;
	private int[][] casesAColorier;
	private int viewX = 0; // Coordonnée x de la vue
	private int viewY = 0; // Coordonnée y de la vue
	private static final int VIEW_SIZE = 100; // Taille de la vue
	JFrame window ;

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
<<<<<<< Updated upstream
			}
		});
		JFrame window = new JFrame();
=======
				refocus();
			}
		});




		window = new JFrame();
>>>>>>> Stashed changes
		window.setSize(largeur*taille_case+50, hauteur*taille_case+50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.add(monBouton, "South");
		window.setFocusable(true);
		window.requestFocusInWindow();
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
		window.setVisible(true);
	}

<<<<<<< Updated upstream
=======
	public void refocus(){
		window.requestFocusInWindow();
	}


>>>>>>> Stashed changes
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
		g.setColor(Color.BLUE);
		for (int i = 0; i < VIEW_SIZE; i++)
		{
			for (int j = 0; j < VIEW_SIZE; j++)
			{
				if (casesAColorier[i][j] == 1)
				{
					g.fillRect(taille_case + (i * taille_case), taille_case + (j * taille_case), taille_case, taille_case);
				}
			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(taille_case, taille_case, VIEW_SIZE*taille_case,VIEW_SIZE*hauteur*taille_case);
		for (int i = taille_case; i <= VIEW_SIZE*taille_case; i += taille_case) {
			g.drawLine(i, taille_case, i, VIEW_SIZE*taille_case+taille_case);
		}
		for (int i = taille_case; i <= VIEW_SIZE*taille_case; i += taille_case) {
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
}
