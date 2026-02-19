public class Adapter {

    String id;
    String inputThreadType;
    String outputThreadType;
    int voltageCapacity;
    Connector sideA;
    Connector sideB;

    public Adapter(String id, String inputThreadType, String outputThreadType, int voltageCapacity){
        this.id = id;
        this.inputThreadType = inputThreadType;
        this.outputThreadType = outputThreadType;
        this.voltageCapacity = voltageCapacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInputThreadType() {
        return inputThreadType;
    }

    public void setInputThreadType(String inputThreadType) {
        this.inputThreadType = inputThreadType;
    }

    public String getOutputThreadType() {
        return outputThreadType;
    }

    public void setOutputThreadType(String outputThreadType) {
        this.outputThreadType = outputThreadType;
    }

    public int getVoltageCapacity() {
        return voltageCapacity;
    }

    public void setVoltageCapacity(int voltageCapacity) {
        this.voltageCapacity = voltageCapacity;
    }

    public Connector getSideA() {
        return sideA;
    }

    public void setSideA(Connector sideA) {
        this.sideA = sideA;
    }

    public Connector getSideB() {
        return sideB;
    }

    public void setSideB(Connector sideB) {
        this.sideB = sideB;
    }

    public String toString(){
        return "";
    }
}
