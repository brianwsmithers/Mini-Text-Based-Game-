package Room;

public class RoomView {

    public void printRoomDetails(RoomModel roomModel) {
        System.out.println(roomModel);
        System.out.println(roomModel.iterateRoomConnection());
    }
}
