

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//Merci à StackOverflow pour sa précieuse contribution !


public class Interface extends JPanel
{
	private int largeur, hauteur, taille_case;

	private int[][] casesAColorier;

	/**
	 * Constructeur.
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 */
	public Interface(int largeur, int hauteur, int taille_case)
	{
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.taille_case = taille_case;
		//tableau de cases à colorier
		casesAColorier = new int[largeur][hauteur];

		JFrame window = new JFrame();
		window.setSize(largeur*taille_case+50, hauteur*taille_case+50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.setVisible(true);
	}

	@Override
	//Fonction d'affichage de la grille.
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = 0; i < largeur; i++)
		{
			for (int j = 0; j < hauteur; j++)
			{
				if (casesAColorier[i][j] == 1)
				{
					g.setColor(Color.RED);
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
