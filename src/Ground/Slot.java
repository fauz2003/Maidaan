package Ground;

public class Slot {
    private int groundId;
    private String slotTimes;
    private String status;

    // Constructor
    public Slot(int groundId, String slotTimes, String status) {
        this.groundId = groundId;
        this.slotTimes = slotTimes;
        this.status = status;
    }

    // Getter methods for groundId, slotTimes, status
    public int getGroundId() {
        return groundId;
    }

    public String getSlotTimes() {
        return slotTimes;
    }

    public String getStatus() {
        return status;
    }

    // Setter methods if needed

    // Optional: Override toString() method for better representation
    @Override
    public String toString() {
        return "Slot{" +
                "groundId=" + groundId +
                ", slotTimes='" + slotTimes + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
