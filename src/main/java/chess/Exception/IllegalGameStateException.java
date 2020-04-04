package chess.Exception;

public class IllegalGameStateException extends IllegalStateException {
    public IllegalGameStateException() {
        super("게임을 진행하기 위해서는 start(시작) 명령어를 먼저 입력해야 합니다.");
    }
}
