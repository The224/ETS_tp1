package model;

import controller.FactoryController;
import model.building.Building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Road implements ISimulatedObject {

    private static Integer ROAD_ALIGNEMENT = 15;

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
        if (newShipment.getOriginFactoryId().equals(this.origin.getId()) ) {
            newShipment.setDestinationFactoryId(this.destination.getId());
            this.shipments.add(newShipment);
            System.out.println("Shipment of  " + newShipment.getComponent() + " is on the road !");
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
                (int) this.origin.getPosition().getX() + ROAD_ALIGNEMENT,
                (int) this.origin.getPosition().getY() + ROAD_ALIGNEMENT,
                (int) this.destination.getPosition().getX() + ROAD_ALIGNEMENT,
                (int) this.destination.getPosition().getY() + ROAD_ALIGNEMENT);

        BufferedImage componentImage = null;
        int shipmentX;
        int shipmentY;
        int shipmentRatio;

        for (Shipment shipment:shipments) {
            try {
                componentImage = ImageIO.read(new File(Component.getURL(shipment.getComponent())));
            } catch (Exception e) {
                e.printStackTrace();
            }

            shipmentRatio = (int)(100 * shipment.getDistance()/roadLength);
            shipmentX = (int)(this.destination.getPosition().getX() * shipmentRatio/100 + this.origin.getPosition().getX());
            shipmentY = (int)(this.destination.getPosition().getY() * shipmentRatio/100 + this.origin.getPosition().getY());

            System.out.println(this.destination.getPosition().getY());
            System.out.println(this.origin.getPosition().getY());
            System.out.println(shipmentY);

            graphics.drawImage(componentImage, shipmentX, shipmentY, null);
        }
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
