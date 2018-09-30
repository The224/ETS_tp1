package model;

import controller.FactoryController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;

public class Warehouse extends Building {

    private HashMap<Component, Integer> inventory = new HashMap<>();


    Warehouse(Integer id, Component output, Integer productionInterval, Point2D position) {
        super(id, output, productionInterval, position);

    }

    public void changeProductionState(Boolean state) {
        FactoryController.isInProduction.emit(state);
    }

    @Override
    public void arrival(Shipment shipment) {
        System.out.println("Warehouse has received " + shipment.getComponent());
        Component component = shipment.getComponent();
        Integer quantity = inventory.get(component);

        if ( inventory.containsKey(component)) {
            inventory.put(component, quantity + 1);
        } else {
            inventory.put(component, 1);
        }

    }

    @Override
    public void nextTurn() {

    }

    @Override
    public void draw(Graphics graphics) {

    }
}
