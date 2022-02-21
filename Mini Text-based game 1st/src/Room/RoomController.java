package Room;

/** The controller must do everything...
 *  The controller calls all methods from either the model or view.
 */
public class RoomController {

    private RoomModel roomModel;
    private RoomView roomView;

    public RoomController(RoomModel roomModel, RoomView roomView) {
        this.roomModel = roomModel;
        this.roomView = roomView;
    }

    public void updateViewPrintRoomDetails() {
        roomView.printRoomDetails(roomModel);
    }


}
