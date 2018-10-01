package simulation;

import controller.FactoryController;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private FactoryController factoryController;

	public PanneauPrincipal(FactoryController factoryController) {
	    this.factoryController = factoryController;
    }

	@Override
	public void paint(Graphics g) {
	    super.paint(g);
        System.out.println("test");

        System.out.println(this.factoryController.getSimulatedObjects());

        this.factoryController.getSimulatedObjects().forEach( ob -> {
            System.out.println(ob);
            try {
               ob.draw(g);
            } catch (Exception e){
                System.out.println(e);
            }
        });
	}

}