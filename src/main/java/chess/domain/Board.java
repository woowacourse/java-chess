package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Board {
    private static final Map<Column, Function<Side, Piece>> BASE_MAP = new HashMap<>();

    private final Map<Position, Piece> board;

    public Board() {
        this.board = initializeBoard();
    }

    static {
        BASE_MAP.put(Column.A, Rook::new);
        BASE_MAP.put(Column.B, Knight::new);
        BASE_MAP.put(Column.C, Bishop::new);
        BASE_MAP.put(Column.D, Queen::new);
        BASE_MAP.put(Column.E, King::new);
        BASE_MAP.put(Column.F, Bishop::new);
        BASE_MAP.put(Column.G, Knight::new);
        BASE_MAP.put(Column.H, Rook::new);
    }

    private Map<Position, Piece> initializeBoard() {
        Map<Position, Piece> result = new HashMap<>();

        setBase(result, Side.WHITE, Row.FIRST);
        setPawn(result, Side.WHITE, Row.SECOND);
        setBlank(result, Row.THIRD);
        setBlank(result, Row.FOURTH);
        setBlank(result, Row.FIFTH);
        setBlank(result, Row.SIXTH);
        setPawn(result, Side.BLACK, Row.SEVENTH);
        setBase(result, Side.BLACK, Row.EIGHTH);
        return result;
    }

    private void setBase(Map<Position, Piece> result, Side side, Row row) {
        for (Column column : Column.values()) {
            result.put(new Position(column, row), BASE_MAP.get(column).apply(side));
        }
    }

    private void setPawn(Map<Position, Piece> result, Side side, Row row) {
        for (Column column : Column.values()) {
            result.put(new Position(column, row), new Pawn(side));
        }
    }

    private void setBlank(Map<Position, Piece> result, Row row) {
        for (Column column : Column.values()) {
            result.put(new Position(column, row), new Blank());
        }
    }

    public String getInitial(Position position) {
        return board.get(position).getInitial();
    }
}
