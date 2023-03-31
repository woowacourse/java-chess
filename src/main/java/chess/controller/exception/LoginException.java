package chess.controller.exception;

public class LoginException extends RuntimeException {

    public LoginException() {
        super("로그인이 필요합니다. login [id] 형식으로 입력해주세요.");
    }
}
