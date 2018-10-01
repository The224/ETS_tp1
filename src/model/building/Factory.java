package model.building;

import controller.FactoryController;
import model.Component;
import model.Shipment;
import model.building.Building;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Factory extends Building {

    private Boolean isInProduction = false;

    public Factory(
            Integer id,
            String type,
            ArrayList<BufferedImage> icons,
            Integer productionInterval,
            Component output,
            HashMap<Component, Integer> productionRequirement,
            Point2D position) {
        super(id, type, icons, productionInterval, output, productionRequirement, position );
        FactoryController.isInProduction.subscribe( (isInProduction) -> this.isInProduction = (Boolean)isInProduction);
    }

    public void shipmentReady() {
        System.out.println("Building " + this.getId() + " has produced " + this.getOutput());
        FactoryController.sendingDispatcher.emit(new Shipment(this.getId(), null, this.getOutput()));
    }

    @Override
    public void nextTurn() {

    }


}
