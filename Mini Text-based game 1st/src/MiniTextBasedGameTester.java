import Reader.TextFileReader;
import Room.RoomController;
import Room.RoomModel;
import Room.RoomView;

public class MiniTextBasedGameTester {
    public static void main(String[] args) {

        TextFileReader tfr = new TextFileReader();
        tfr.readTextFile();

        RoomView livingRoomView = new RoomView();
        RoomModel livingRoomModel = new RoomModel(tfr.getRoomNumber(), tfr.getRoomName(), tfr.getRoomDescription(),
                tfr.isVisited(), tfr.getRoomConnections());

        RoomController roomController = new RoomController(livingRoomModel, livingRoomView);

        roomController.updateViewPrintRoomDetails();



    }
}
