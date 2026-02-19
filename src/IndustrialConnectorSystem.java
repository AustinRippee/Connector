import java.util.Arrays;
import java.util.List;

public class IndustrialConnectorSystem {

    public static void main(String[] args) throws Exception {

        ConnectionSystem system = new ConnectionSystem();

        // Create connectors
        Connector c1 = new Connector("C1", "metric", 10.0, "steel", 120);
        Connector c2 = new Connector("C2", "metric", 11.5, "aluminum", 120);
        Connector c3 = new Connector("C3", "imperial", 12.0, "steel", 240);
        Connector c4 = new Connector("C4", "imperial", 12.5, "brass", 0);

        // Try direct connection
        boolean result = system.connectDirectly(c1, c2);
        System.out.println(result);
        // Returns true - same thread type, compatible diameter and voltage

        // Try incompatible connection
        result = system.connectDirectly(c1, c3);
        System.out.println(result);
        // Returns false - different thread types

        // Create adapter to bridge metric and imperial
        Adapter adapter1 = new Adapter("A1", "metric", "imperial", 300);

        // Connect using adapter
        result = system.canConnectWithAdapter(c1, c3, adapter1);
        System.out.println(result);
        // Returns false - c1 is already connected to c2

        // Disconnect c1 first
        system.disconnect(c1);

        // Now try with adapter
        result = system.connectWithAdapter(c1, c3, adapter1);
        System.out.println(result);
        // Returns true - adapter bridges the thread type difference

        // Try to connect electrical to non-electrical with adapter
        Adapter adapter2 = new Adapter("A2", "imperial", "imperial", 250);
        result = system.connectWithAdapter(c3, c4, adapter2);
        System.out.println(result);
        // Returns true - adapter handles voltage conversion (c3: 240V, c4: 0V)

        // Generate report
        List<Connector> allConnectors = Arrays.asList(c1, c2, c3, c4);
        List<Adapter> allAdapters = Arrays.asList(adapter1, adapter2);
        String report = system.getConnectionReport(allConnectors, allAdapters);
        System.out.println();
        System.out.println();
        System.out.println(report);
    }
}
