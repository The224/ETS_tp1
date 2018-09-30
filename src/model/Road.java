package model;

import controller.FactoryController;

import java.awt.*;
import java.util.ArrayList;

public class Road implements IDessinable, Observer<Shipment>{

    private Integer originFactoryId;
    private Integer destinationFactoryId;


    private ArrayList<Shipment> shipments = new ArrayList<>();

    public Road(Integer originX, Integer destination) {
        FactoryController.sendingDispatcher.subscribe(this);
    }

    public void deliver(Shipment shipment) {
        FactoryController.reicevingDispatcher.emit(shipment);
    }

    @Override
    public void subjectChange(Shipment newShipment) {
        if (newShipment.getOriginFactoryId() == this.originFactoryId) {
            newShipment.setDestinationFactoryId(this.destinationFactoryId);
            this.shipments.add(newShipment);
        }
    }

    @Override
    public void draw(Graphics graphics) {

    }

}
