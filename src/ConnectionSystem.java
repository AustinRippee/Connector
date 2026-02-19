import java.util.ArrayList;
import java.util.List;

public class ConnectionSystem {

    boolean canConnectDirectly(Connector c1, Connector c2) {

        if (!(c1.getThreadType().equals(c2.getThreadType()))) {
            return false;
        } else if (!(Math.abs(c1.getDiameter() - c2.getDiameter()) <= 2)) {
            return false;
        } else if (c1.getVoltage() == 0 && c2.getVoltage() == 0) {
            return false;
        } else if (c1.isConnected || c2.isConnected) {
            return false;
        } else if (c1 == c2) {
            return false;
        } else {
            return true;
        }
    }

    boolean connectDirectly(Connector c1, Connector c2) {
        if (canConnectDirectly(c1, c2)) {
            c1.setConnected(true);
            c2.setConnected(true);
            c1.setConnectedTo(c2);
            c2.setConnectedTo(c1);
            return true;
        }
        return false;
    }

    boolean canConnectWithAdapter(Connector c1, Connector c2, Adapter adapter) throws Exception {
        if (!(c1.getThreadType().equals(adapter.getInputThreadType()))) {
            return false;
        } else if (!(c2.getThreadType().equals(adapter.getOutputThreadType()))) {
            return false;
        } else if (Math.abs(c1.getDiameter() - c2.getDiameter()) > 5) {
            return false;
        } else if (!(Math.max(c1.getVoltage(), c2.getVoltage()) <= adapter.getVoltageCapacity())) { // I had to use ChatGPT on this one
            //the problem was that I was using a boolean in my logic - else if (!((c1.getVoltage() > 0 || c2.getVoltage() > 0) <= adapter.getVoltageCapacity()))
            return false;
        } else if (c1.isConnected() || c2.isConnected()) {
            throw new Exception("Both connectors need to be disconnected.");
        } else if (!(adapter.getSideA() == null && adapter.getSideB() == null)) {
            return false;
        } else {
            return true;
        }
    }

    boolean connectWithAdapter(Connector c1, Connector c2, Adapter adapter) throws Exception {
        if (canConnectWithAdapter(c1, c2, adapter)) {
            c1.setConnected(true);
            c1.setConnectedTo(c2);
            c2.setConnected(true);
            c2.setConnectedTo(c1);
            adapter.setSideA(c1);
            adapter.setSideB(c2);
            return true;
        }
        return false;
    }

    boolean disconnect(Connector c1) {

        if (c1.isConnected) {
            Connector partner = c1.getConnectedTo();
            c1.setConnected(false);
            c1.setConnectedTo(null);
            partner.setConnected(false);
            partner.setConnectedTo(null);

            //struggling to get the adapter check
            //I know that the pseudocode would look like this:
            //if (isConnectedToAdapter()){
            //  adapter.setSideA(null);
            //  adapter.setSideB(null);

            return true;
        }
        return false;
    }

    String getConnectionReport(List<Connector> connectors, List<Adapter> adapters) {

        List<Connector> totalConnectorsConnected = new ArrayList<>();
        List<Connector> totalConnectorsNotConnected = new ArrayList<>();

        List<Adapter> adaptersConnectedList = new ArrayList<>();
        List<Adapter> adaptersNotConnectedList = new ArrayList<>();

        for (Connector connector : connectors) {
            if (connector.isConnected) {
                totalConnectorsConnected.add(connector);
            } else {
                totalConnectorsNotConnected.add(connector);
            }
        }

        for (Adapter adapter : adapters) {
            //still struggling to get adapters
        }

        return "CONNECTION SYSTEM REPORT" + "\n"
                + "========================" + "\n"
                + "Total Connectors: " + connectors.size() + "\n"
                + "Connected: " + totalConnectorsConnected + "\n"
                + "Unconnected: " + totalConnectorsNotConnected + "\n"
                + "\n"
                + "Direct Connections:" + "\n"
                + connectors + "\n"
                + "\n"
                + "Adapter Connections:"
                + adaptersConnectedList + "\n"
                + "\n"
                + "Unconnected Connectors:"
                + adaptersNotConnectedList
                ;

    }
}