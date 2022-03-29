package chess.domain.state;

import chess.Command;
import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;

public abstract class Running implements ChessState {

    private static final String INVALID_MOVING_COMMAND = "올바르지 않은 이동 명령입니다.";
    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    protected final Board board;
    protected final Color color;

    public Running(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public ChessState execute(Command command, String... moveArgs) {
        if (command == Command.MOVE) {
            validateMoveArgs(moveArgs.length);
            return move(parseStringToPosition(moveArgs[0]),
                    parseStringToPosition(moveArgs[1]));
        }
        if (command == Command.END) {
            return new End(board);
        }
        if (command == Command.STATUS) {
            return this;
        }
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
    }

    private void validateMoveArgs(int length) {
        if (length != 2) {
            throw new IllegalArgumentException(INVALID_MOVING_COMMAND);
        }
    }

    private Position parseStringToPosition(final String rawPosition) {
        if (rawPosition.length() != 2) {
            throw new IllegalArgumentException(INVALID_MOVING_COMMAND);
        }
        final String[] separatedPosition = rawPosition.split("");
        final Column column = Column.from(separatedPosition[0]);
        final Row row = Row.from(separatedPosition[1]);
        return new Position(column, row);
    }

    protected abstract ChessState move(Position start, Position target);
}
