package simulation;

import controller.ConfigurationReader;
import controller.FactoryController;

public class Simulation {

	public static void main(String[] args) {


        FactoryController factoryController = new FactoryController();

		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale(factoryController);

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}
