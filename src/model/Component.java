package model;

public enum Component {
    METAL,
    WING,
    MOTOR,
    PLANE;

    // TODO Set urls
    public static String getURL(Component component) {
        switch (component){
            case METAL:
                return "url";
            case WING:
                return "url";
            case MOTOR:
                return "url";
            case PLANE:
                return "url";
        }
        return "";
    }

    public static Component getComponentFromFR(String name) {
        switch (name){
            case "metal":
                return METAL;
            case "aile":
                return WING;
            case "moteur":
                return MOTOR;
            case "avion":
                return PLANE;
        }
        return null;
    }

}
