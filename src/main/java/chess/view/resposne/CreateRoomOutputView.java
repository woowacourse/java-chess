package chess.view.resposne;

import chess.controller.room.create.CreateRoomOutput;

public class CreateRoomOutputView implements CreateRoomOutput {

    @Override
    public void printCreateRoomSuccess(int roomId) {
        System.out.println("게임을 생성했습니다");
        System.out.println("게임 번호는 " + roomId + " 입니다");
        System.out.println("게임을 참여하려면 join [게임 번호]를 입력하세요");
    }
}
