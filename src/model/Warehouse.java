package model;

import controller.FactoryController;

import java.awt.*;

public class Warehouse extends Building {

    Warehouse(Integer id, Component output, Integer productionInterval) {
        super(id, output, productionInterval);

    }

    public void changeProductionState(Boolean state) {
        FactoryController.isInProduction.emit(state);
    }

    @Override
    public void estimateTime() {

    }

    @Override
    public void process() {

    }

    @Override
    public void draw(Graphics graphics) {

    }
}
