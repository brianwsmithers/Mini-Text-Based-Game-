package Player;


public class PlayerView {

    public void failedRoomTraversal(boolean traverse) {
        if (!traverse) {
            System.out.println("You can't travel here...");
        }
    }
}
