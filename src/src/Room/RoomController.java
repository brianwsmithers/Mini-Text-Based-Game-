package Room;

public class RoomController {
    private Room model;
    private RoomView view;

    public RoomController(Room model, RoomView view) {
        this.model = model;
        this.view = view;
    }

    public void setModel(Room model) {
        this.model = model;
    }

    public Room getModel() {
        return model;
    }

    public void printRoomPrompt() {
        view.printRoomPrompt(model);
    }
}
