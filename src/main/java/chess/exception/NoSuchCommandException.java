package chess.exception;

public class NoSuchCommandException extends RuntimeException {

    public NoSuchCommandException() {
        super("[ERROR] 존재하지 않는 명령어를 입력했습니다.");
    }
}
