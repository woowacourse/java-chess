package chess.domain.state.exception;

import chess.domain.state.command.CommandType;

public class UnsupportedCommandException extends RuntimeException {

    public static final String FORMAT = "현재 상황에서 지원하지 않는 명령입니다.(%s)";

    public UnsupportedCommandException(final CommandType commandType) {
        super(String.format(FORMAT, commandType));
    }

    public UnsupportedCommandException(final String reason) {
        super(String.format(FORMAT, reason));
    }
}
