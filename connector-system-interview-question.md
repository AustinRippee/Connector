# Programming Interview Question: Industrial Connector System

**Difficulty:** Mid/Senior-level

## Problem Statement

You're designing a system for an industrial manufacturing plant that connects different mechanical components. The system has connectors that can only attach under specific conditions, and adapters that enable incompatible connectors to work together.

## Object Specifications

### Part 1: Create the Connector Class

Create a `Connector` class representing mechanical connectors with the following properties:
- `id` (String) - unique identifier
- `threadType` (String) - thread pattern: "metric", "imperial", "custom"
- `diameter` (double) - connector diameter in millimeters
- `material` (String) - material type: "steel", "aluminum", "brass", "plastic"
- `voltage` (int) - maximum voltage rating in volts (0 if non-electrical)
- `isConnected` (boolean) - current connection status
- `connectedTo` (Connector) - reference to connected connector (null if not connected)

Requirements:
- Constructor accepting all properties except `isConnected` and `connectedTo` (default to false and null)
- Implement getters and setters
- Override `toString()` for readable output

### Part 2: Create the Adapter Class

Create an `Adapter` class that enables incompatible connectors to work together:
- `id` (String) - unique identifier
- `inputThreadType` (String) - thread type it accepts
- `outputThreadType` (String) - thread type it converts to
- `voltageCapacity` (int) - maximum voltage it can handle
- `sideA` (Connector) - connector attached to input side (null if empty)
- `sideB` (Connector) - connector attached to output side (null if empty)

Requirements:
- Constructor accepting id, inputThreadType, outputThreadType, voltageCapacity
- Implement getters and setters
- Override `toString()`

### Part 3: Create the ConnectionSystem Class

Create a `ConnectionSystem` class that manages connections with these methods:

#### 1. `boolean canConnectDirectly(Connector c1, Connector c2)`
Two connectors can connect directly if ALL conditions are met:
- Both have the same `threadType`
- Diameter difference is within 2mm (tolerance)
- At least one is non-electrical (voltage = 0) OR both have voltage > 0
- Neither is already connected
- They are not the same connector

#### 2. `boolean connectDirectly(Connector c1, Connector c2)`
- Check if they can connect using `canConnectDirectly()`
- If yes: update both connectors' status to connected and link them together
- Return true on success, false on failure

#### 3. `boolean canConnectWithAdapter(Connector c1, Connector c2, Adapter adapter)`
Check if an adapter can bridge two incompatible connectors:
- c1's threadType must match adapter's inputThreadType
- c2's threadType must match adapter's outputThreadType
- Diameter difference between c1 and c2 must be within 5mm (adapters provide more tolerance)
- If either connector has voltage > 0, both voltages must be ≤ adapter's voltageCapacity
- Neither connector is already connected
- Adapter's sideA and sideB are both empty

#### 4. `boolean connectWithAdapter(Connector c1, Connector c2, Adapter adapter)`
- Check if connection is possible using `canConnectWithAdapter()`
- If yes:
  - Set c1 as connected, linked to c2
  - Set c2 as connected, linked to c1
  - Set adapter's sideA to c1
  - Set adapter's sideB to c2
- Return true on success, false on failure

#### 5. `boolean disconnect(Connector c1)`
- If c1 is connected, disconnect it and its partner
- If connected via adapter, clear the adapter's sides
- Return true if disconnection happened, false if already disconnected

#### 6. `String getConnectionReport(List<Connector> connectors, List<Adapter> adapters)`
Generate a report showing:
- Total connectors and how many are connected
- List of direct connections
- List of adapter-based connections
- List of unconnected connectors

## Example Usage:

```java
ConnectionSystem system = new ConnectionSystem();

// Create connectors
Connector c1 = new Connector("C1", "metric", 10.0, "steel", 120);
Connector c2 = new Connector("C2", "metric", 11.5, "aluminum", 120);
Connector c3 = new Connector("C3", "imperial", 12.0, "steel", 240);
Connector c4 = new Connector("C4", "imperial", 12.5, "brass", 0);

// Try direct connection
boolean result = system.connectDirectly(c1, c2);
// Returns true - same thread type, compatible diameter and voltage

// Try incompatible connection
result = system.connectDirectly(c1, c3);
// Returns false - different thread types

// Create adapter to bridge metric and imperial
Adapter adapter1 = new Adapter("A1", "metric", "imperial", 300);

// Connect using adapter
result = system.canConnectWithAdapter(c1, c3, adapter1);
// Returns false - c1 is already connected to c2

// Disconnect c1 first
system.disconnect(c1);

// Now try with adapter
result = system.connectWithAdapter(c1, c3, adapter1);
// Returns true - adapter bridges the thread type difference

// Try to connect electrical to non-electrical with adapter
Adapter adapter2 = new Adapter("A2", "imperial", "imperial", 250);
result = system.connectWithAdapter(c3, c4, adapter2);
// Returns true - adapter handles voltage conversion (c3: 240V, c4: 0V)

// Generate report
List<Connector> allConnectors = Arrays.asList(c1, c2, c3, c4);
List<Adapter> allAdapters = Arrays.asList(adapter1, adapter2);
String report = system.getConnectionReport(allConnectors, allAdapters);
System.out.println(report);
```

## Expected Output:
```
CONNECTION SYSTEM REPORT
========================
Total Connectors: 4
Connected: 3
Unconnected: 1

Direct Connections:
(none)

Adapter Connections:
C1 (metric, steel) <--[A1]--> C3 (imperial, steel)
C3 (imperial, steel) <--[A2]--> C4 (imperial, brass)

Unconnected Connectors:
C2 (metric, aluminum)
```

## Bonus Challenges:

1. **Connection Chain Tracking**: Implement `List<Connector> getConnectionChain(Connector start)` that returns all connectors in a chain starting from a given connector.

2. **Material Compatibility**: Add a rule that brass connectors cannot directly connect to aluminum (corrosion risk), but can connect via steel adapters.

3. **Load Capacity**: Add a `loadCapacity` property and ensure the weakest link in a chain can handle the required load.

---

**What This Tests:**
- Multi-class object-oriented design
- Object relationships and state management
- Complex validation logic with multiple conditions
- Reference management between objects
- Business logic implementation
- Edge case handling
- System-level thinking and interactions
- Bidirectional relationships
