package chess.domain.state;

import chess.Command;
import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

public abstract class Running implements ChessState {

    private static final String INVALID_MOVING_COMMAND = "올바르지 않은 이동 명령입니다.";
    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";
    private static final int MOVE_ARGUMENT_LENGTH = 3;
    private static final int START_POSITION_ARGUMENT_INDEX = 1;
    private static final int END_POSITION_ARGUMENT_INDEX = 2;
    private static final int POSITION_ARGUMENT_LENGTH = 2;
    private static final int COLUMN_ARGUMENT_INDEX = 0;
    private static final int ROW_ARGUMENT_INDEX = 1;


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
            return move(parseStringToPosition(moveArgs[START_POSITION_ARGUMENT_INDEX]),
                    parseStringToPosition(moveArgs[END_POSITION_ARGUMENT_INDEX]));
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
        if (length != MOVE_ARGUMENT_LENGTH) {
            throw new IllegalArgumentException(INVALID_MOVING_COMMAND);
        }
    }

    private Position parseStringToPosition(final String rawPosition) {
        if (rawPosition.length() != POSITION_ARGUMENT_LENGTH) {
            throw new IllegalArgumentException(INVALID_MOVING_COMMAND);
        }
        final String[] separatedPosition = rawPosition.split("");
        final Column column = Column.from(separatedPosition[COLUMN_ARGUMENT_INDEX]);
        final Row row = Row.from(separatedPosition[ROW_ARGUMENT_INDEX]);
        return new Position(column, row);
    }

    protected abstract ChessState move(Position start, Position target);

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }

    @Override
    public Status createStatus() {
        return new Status(board);
    }
}
