public class Connector {

    String id;
    String threadType;
    double diameter;
    String material;
    int voltage;
    boolean isConnected = false;
    Connector connectedTo = null;

    public Connector(String id, String threadType, double diameter, String material, int voltage){
        this.id = id;
        this.threadType = threadType;
        this.diameter = diameter;
        this.material = material;
        this.voltage = voltage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThreadType() {
        return threadType;
    }

    public void setThreadType(String threadType) {
        this.threadType = threadType;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public Connector getConnectedTo() {
        return connectedTo;
    }

    public void setConnectedTo(Connector connectedTo) {
        this.connectedTo = connectedTo;
    }

    @Override
    public String toString(){
        return "";
    }



}
