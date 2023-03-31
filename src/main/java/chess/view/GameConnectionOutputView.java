package chess.view;

import chess.domain.userAccess.room.Room;

import java.util.List;

public class GameConnectionOutputView {

    public void printInputIdMessage() {
        System.out.println("아이디를 입력해주세요.");
    }

    public void printMakeNewRoomMessage() {
        System.out.println("새로운 게임을 만듭니다.");
    }

    public void printPlaySavedRoomMessage() {
        System.out.println("저장되어 있던 게임을 실행합니다.");
    }

    public void printSavedRooms(List<Room> rooms) {
        for (Room room : rooms) {
            System.out.println(room.roomId() + "번 방");
        }
    }

    public void printSelectRoomMessage() {
        System.out.println("재시작하려면 게임 방의 숫자를 입력해주세요.\n" + "새로운 게임을 만들고 싶으시면 0을 입력해주세요.");
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
        System.out.println();
    }
}
