package chess.controller.exception;

public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException() {
        super("방 잘못된 방번호를 입력하셨습니다 -join [방 번호] 형식으로 입력해주세요. -games 로 방 목록을 확인할 수 있습니다.");
    }
}
