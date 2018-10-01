package model.building;

import controller.FactoryController;
import model.Component;
import model.building.Building;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse extends Building {

    public Warehouse(
            Integer id,
            String type,
            ArrayList<BufferedImage> icons,
            HashMap<Component, Integer> productionRequirement,
            Point2D position) {
        super(id, type, icons, null, null, productionRequirement, position );

    }

    public void changeProductionState(Boolean state) {
        FactoryController.isInProduction.emit(state);
    }

    @Override
    public void nextTurn() {

    }

}
