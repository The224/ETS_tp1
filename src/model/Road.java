package model;

import controller.FactoryController;
import model.building.Building;

import java.awt.*;
import java.util.ArrayList;

public class Road implements ISimulatedObject {

    private Building origin;
    private Building destination;
    private Double roadLength;

    private ArrayList<Shipment> shipments = new ArrayList<>();

    public Road(Building origin, Building destination) {
        this.origin = origin;
        this.destination = destination;
        this.roadLength = computeRoadLength(origin, destination);

        FactoryController.sendingDispatcher.subscribe(shipment -> this.addNewShipment((Shipment) shipment));
    }

    private Boolean addNewShipment(Shipment newShipment) {
        System.out.println("Shipment of  " + newShipment.getComponent() + " is on the road !");
        if (newShipment.getOriginFactoryId().equals(this.origin.getId()) ) {
            newShipment.setDestinationFactoryId(this.destination.getId());
            this.shipments.add(newShipment);
            return true;
        }
        return false;
    }

    @Override
    public void nextTurn() {
        for (Shipment shipment : shipments) {
            if(shipment.travel() > this.roadLength) {
                this.deliver(shipment);
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawLine(
                (int) this.origin.getPosition().getX(),
                (int) this.origin.getPosition().getY(),
                (int) this.destination.getPosition().getX(),
                (int) this.destination.getPosition().getY());
    }

    private void deliver(Shipment shipment) {
        System.out.println("Road is delivering " + shipment.getComponent());
        this.destination.arrival(shipment);
        shipments.remove(shipment);
    }

    private static Double computeRoadLength(Building origin, Building destination) {
        return origin.getPosition().distance(destination.getPosition());
    }
}
