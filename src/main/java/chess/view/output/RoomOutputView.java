package chess.view.output;

import chess.domain.room.Room;
import java.util.List;

public class RoomOutputView {
    public void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printRooms(final List<Room> rooms) {
        for (Room room : rooms) {
            System.out.println("> " + room.getId() + ". " + room.getName());
        }
    }

    public void printSaveSuccess(final String roomName) {
        System.out.println(roomName + " 방 생성 완료");
    }
}
