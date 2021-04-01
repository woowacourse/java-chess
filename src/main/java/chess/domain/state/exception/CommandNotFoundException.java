package chess.domain.state.exception;

public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException(final String command) {
        super(String.format("존하지 않는 커맨드 입니다.(%s)", command));
    }
}
