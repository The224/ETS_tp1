package model;

import controller.FactoryController;

import java.awt.*;
import java.util.ArrayList;

public class Road implements IDessinable {

    private Integer originFactoryId;
    private Integer destinationFactoryId;

    private ArrayList<Shipment> shipments = new ArrayList<>();

    public Road(Integer originX, Integer destination) {
        FactoryController.sendingDispatcher.subscribe(shipment -> this.addNewShipment((Shipment) shipment));
    }

    public void deliver(Shipment shipment) {
        FactoryController.reicevingDispatcher.emit(shipment);
    }


    public Boolean addNewShipment(Shipment newShipment) {
        System.out.println(newShipment);
        if (newShipment.getOriginFactoryId() == this.originFactoryId) {
            newShipment.setDestinationFactoryId(this.destinationFactoryId);
            this.shipments.add(newShipment);
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics graphics) {

    }

}
