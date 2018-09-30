package model;

import controller.FactoryController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;

public class Factory extends Building {

    private Boolean isInProduction = false;

    private HashMap<Component, Integer> inventory = new HashMap<>();

    public Factory(Integer id, Component output, Integer productionInterval, Point2D position) {
        super(id, output, productionInterval, position);
        FactoryController.isInProduction.subscribe( (isInProduction) -> this.isInProduction = (Boolean)isInProduction);
    }

    public void shipmentReady() {
        System.out.println("Building " + this.getId() + " has produced " + this.getOutput());
        FactoryController.sendingDispatcher.emit(new Shipment(this.getId(), null, this.getOutput()));
    }

    public void arrival(Shipment shipment) {
        Component component = shipment.getComponent();
        Integer quantity = inventory.get(component);

        if ( inventory.containsKey(component)) {
            inventory.put(component, quantity + 1);
        } else {
            inventory.put(component, 1);
        }
        System.out.println("Building " + this.getId() + " has received " + shipment.getComponent());
    }

    @Override
    public void nextTurn() {

    }

    @Override
    public void draw(Graphics graphics) {

    }
}
