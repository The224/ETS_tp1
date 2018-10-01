package simulation;

import controller.FactoryController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	private static final Dimension DIMENSION = new Dimension(700, 700);

	public FenetrePrincipale(FactoryController factoryController) {
		PanneauPrincipal panneauPrincipal = new PanneauPrincipal(factoryController);
		MenuFenetre menuFenetre = new MenuFenetre();
		add(panneauPrincipal);
		add(menuFenetre, BorderLayout.NORTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("TEST")) {
			repaint();
			System.out.println(evt.getNewValue());
		}
	}
}
