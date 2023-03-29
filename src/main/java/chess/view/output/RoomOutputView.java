package chess.view.output;

import chess.domain.room.Room;
import java.util.List;

public class RoomOutputView {
    public void printCommands(final String user, final String room) {
        System.out.println("[로그인 한 유저: " + user + ", 선택한 방 이름: " + room + "]");
        System.out.println("> 방 조회 : history");
        System.out.println("> 방 생성 : create 방이름 - 예) create room1");
        System.out.println("> 방 참가 : join 방번호 - 예) join 1");
        System.out.println("> 메인 화면 : end");
    }

    public void printRooms(final List<Room> rooms) {
        for (Room room : rooms) {
            System.out.println("> " + room.getId() + ". " + room.getName());
        }
    }

    public void printSaveSuccess(final String roomName) {
        System.out.println(roomName + " 방 생성 완료");
    }

    public void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
