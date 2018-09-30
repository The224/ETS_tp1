package model;

public class Shipment {
    private Integer originFactoryId;
    private Integer destinationFactoryId;
    private Component component;
    private Integer distance;

    public Shipment(Integer originFactoryId, Integer destinationFactoryId, Component component) {
        this.originFactoryId = originFactoryId;
        this.destinationFactoryId = destinationFactoryId;
        this.component = component;
        this.distance = 0;
    }

    public Integer getOriginFactoryId() {
        return originFactoryId;
    }


    public void setDestinationFactoryId(Integer destinationFactoryId) {
        this.destinationFactoryId = destinationFactoryId;
    }

    public Integer travel() {
        return travel(1);
    }

    public Integer travel(Integer distance) {
        return this.distance += distance;
    }

    public Component getComponent() {
        return component;
    }


    @Override
    public String toString() {
        return "Shipment{" +
                "originFactoryId=" + originFactoryId +
                ", destinationFactoryId=" + destinationFactoryId +
                ", component=" + component +
                ", distance=" + distance +
                '}';
    }
}
