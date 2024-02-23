package Ground;

public class Ground {
    private int groundId;
    private String name;
    private String location;
    private String inventory;
    private int managerId;
    private String slotTimes;

    // Constructor
    public Ground(int groundId, String name, String location, String inventory, int managerId, String slotTimes) {
        this.groundId = groundId;
        this.name = name;
        this.location = location;
        this.inventory = inventory;
        this.managerId = managerId;
        this.slotTimes = slotTimes;
    }

    // Getter for groundId
    public int getGroundId() {
        return groundId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Getter for inventory
    public String getInventory() {
        return inventory;
    }

    // Getter for managerId
    public int getManagerId() {
        return managerId;
    }

    // Getter for slotTimes
    public String getSlotTimes() {
        return slotTimes;
    }

    // Setter for slotTimes
    public void setSlotTimes(String newSlotTimes) {
        this.slotTimes = newSlotTimes;
    }

    // Example usage
    public static void main(String[] args) {
        // Create a Ground instance
        Ground ground1 = new Ground(1, "Sports Ground", "City Park", "Football, Basketball", 101, "9 AM - 5 PM");

        // Display ground details
        System.out.println("ID: " + ground1.getGroundId());
        System.out.println("Name: " + ground1.getName());
        System.out.println("Location: " + ground1.getLocation());
        System.out.println("Inventory: " + ground1.getInventory());
        System.out.println("Manager ID: " + ground1.getManagerId());
        System.out.println("Slot Times: " + ground1.getSlotTimes());

        // Change the slot times
        ground1.setSlotTimes("2 PM - 8 PM");
        System.out.println("New Slot Times: " + ground1.getSlotTimes());
    }
}