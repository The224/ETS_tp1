package model;

public abstract class Building implements IDessinable, Observer<Shipment> {

    Integer id;
    String output;
    Integer productionInterval;

    public Building(Integer id, String output, Integer productionInterval) {
        this.id = id;
        this.output = output;
        this.productionInterval = productionInterval;
    }

    abstract void estimateTime();
    abstract void process();

}

