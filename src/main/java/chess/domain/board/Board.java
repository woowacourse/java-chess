package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;

public class Board {
    private final Map<Position, Optional<Piece>> board;

    private Board(final Map<Position, Optional<Piece>> board) {
        this.board = board;
    }

    public static Board init() {
        return new Board(createInitial());
    }

    // TODO : 네이밍 변경
    private static Map<Position, Optional<Piece>> createInitial() {
        Map<Position, Optional<Piece>> tmpBoard = new HashMap<>();

        // TODO : 스트림 적용
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                tmpBoard.put(Position.of(row, column), findInitialConditionByPosition(Position.of(row, column)));
            }
        }

        return tmpBoard;
    }

    // TODO : 네이밍 변경
    private static Optional<Piece> findInitialConditionByPosition(final Position position) {
        for (Type type : Type.values()) {
            if (type.initPosition(position, Side.WHITE)) {
                return Optional.of(new Piece(type, Side.WHITE));
            }

            if (type.initPosition(position, Side.BLACK)) {
                return Optional.of(new Piece(type, Side.BLACK));
            }
        }

        return Optional.empty();
    }

    public Map<Position, Optional<Piece>> getBoard() {
        return board;
    }
}
