import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Merci à StackOverflow pour sa précieuse contribution !


public class Interface extends JPanel
{
	private int largeur, hauteur, taille_case;
	private boolean play = true;
	private int[][] casesAColorier;
	private int viewX = 0; // Position de la vue sur l'axe X
	private int viewY = 0; // Position de la vue sur l'axe Y
	private static final int VIEW_SIZE = 100; // Taille de la vue



	/**
	 * Constructeur.
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 */
	public Interface(int largeurs, int hauteurs, int taille_case)
	{

		this.largeur = 100;
		this.hauteur = 100;
		this.taille_case = taille_case;
		//tableau de cases à colorier
		casesAColorier = new int[largeurs][hauteurs];
		JButton monBouton = new JButton("STOP");
		monBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play = !play;
			}
		});




		JFrame window = new JFrame();
		window.setSize(largeur*taille_case+50, hauteur*taille_case+50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.add(monBouton, "South");
		window.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				// Utilisez un switch ou des if pour gérer les différentes touches
				switch (key) {
					case KeyEvent.VK_UP:
                    	viewY = Math.max(0, viewY - 1); // Déplace la vue vers le haut
                    	break;
                	case KeyEvent.VK_DOWN:
                    	viewY = Math.min(hauteurs - VIEW_SIZE, viewY + 1); // Déplace la vue vers le bas
                    	break;
                	case KeyEvent.VK_LEFT:
                    	viewX = Math.max(0, viewX - 1); // Déplace la vue vers la gauche
                   		break;
                	case KeyEvent.VK_RIGHT:
                    	viewX = Math.min(largeurs - VIEW_SIZE, viewX + 1); // Déplace la vue vers la droite
                    	break;
				}
				repaint();
			}
		});


		window.setVisible(true);
	}

	public boolean getPlay(){
		return play;
	}

	@Override
	//Fonction d'affichage de la grille.
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = viewX; i < viewX+VIEW_SIZE; i++)
		{
			for (int j = viewY; j < viewY+VIEW_SIZE; j++)
			{
				if (casesAColorier[i][j] == 1)
				{
					g.setColor(Color.BLUE);
					g.fillRect(taille_case + (i * taille_case), taille_case + (j * taille_case), taille_case, taille_case);
				}
			}
		}


		g.setColor(Color.BLACK);
		g.drawRect(taille_case, taille_case, largeur*taille_case, hauteur*taille_case);

		for (int i = taille_case; i <= largeur*taille_case; i += taille_case) {
			g.drawLine(i, taille_case, i, hauteur*taille_case+taille_case);
		}

		for (int i = taille_case; i <= hauteur*taille_case; i += taille_case) {
			g.drawLine(taille_case, i, largeur*taille_case+taille_case, i);
		}
	}

	/**
	 * Fonction permettant de colorier, en rouge, une case de la grille
	 * @param x Abscisse de la case à colorier (entre 0 et largeur de grille - 1).
	 * @param y Ordonnée de la case à colorier (entre 0 et hauteur de grille - 1).
	 */
	public void colorierCase(int x, int y)
	{
		casesAColorier[x][y] = 1;
	}
    public void tuerCase(int x, int y){
		casesAColorier[x][y] = 0;
    }

	/**
	 * Accesseur.
	 * @return Renvoie la largeur de la grille
	 */
	public int getLargeur()
	{
		return largeur;
	}

	/**
	 * Accesseur.
	 * @return Renvoie la hauteur de la grille
	 */
	public int getHauteur()
	{
		return hauteur;
	}
}
