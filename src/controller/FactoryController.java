package controller;

import model.Factory;
import model.Shipment;
import model.Subject;
import model.Warehouse;

import java.util.ArrayList;

public class FactoryController {

    public static Subject<Shipment> sendingDispatcher = new Subject<>();
    public static Subject<Boolean> isInProduction = new Subject<>();

    private ArrayList<Factory> factories;
    private Warehouse warehouse;

    public FactoryController() {
         factories= new ArrayList<>();
    }

}
