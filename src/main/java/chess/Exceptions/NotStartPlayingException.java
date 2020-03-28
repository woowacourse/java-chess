package chess.Exceptions;

public class NotStartPlayingException extends RuntimeException{
    public NotStartPlayingException() {
        super("게임을 진행하기 위해서는 start(시작) 명령어를 먼저 입력해야 합니다.");
    }
}
