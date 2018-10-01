package simulation;

import controller.FactoryController;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {

	private boolean actif = true;
	private static final int DELAI = 100;

	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			firePropertyChange("TEST", null, "Ceci est un test");
		}
		return null;
	}

}