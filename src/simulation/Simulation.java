package simulation;

import controller.FactoryController;
import model.Component;
import model.Factory;
import model.Road;
import model.Shipment;

public class Simulation {

	private FactoryController factoryController = new FactoryController();

	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static void main(String[] args) {
		Road road = new Road(1,2);
        Factory factory = new Factory(1, Component.METAL,2);

        factory.shipmentReady();

//		Environnement environnement = new Environnement();
//		FenetrePrincipale fenetre = new FenetrePrincipale();
//
//		environnement.addPropertyChangeListener(fenetre);
//		environnement.execute();
	}

}
