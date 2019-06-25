package chess.model.board;

import chess.model.Column;
import chess.model.Row;
import chess.model.Square;
import chess.model.unit.*;

import java.util.HashMap;
import java.util.Map;

public class BasicBoardInitializer implements BoardInitializer{
    @Override
    public Map<Square, Piece> initialize() {
        Map<Square, Piece> board = new HashMap<>();
        setFirstLine(board, Side.WHITE, Column._1);
        setSecondLine(board, Side.WHITE, Column._2);
        setSecondLine(board, Side.BLACK, Column._7);
        setFirstLine(board, Side.BLACK, Column._8);
        return board;
    }

    private void setFirstLine(Map<Square, Piece> map, Side side, Column column) {
        map.put(Square.of(column, Row.A), new Rook(side));
        map.put(Square.of(column, Row.B), new Knight(side));
        map.put(Square.of(column, Row.C), new Bishop(side));
        map.put(Square.of(column, Row.D), new Queen(side));
        map.put(Square.of(column, Row.E), new King(side));
        map.put(Square.of(column, Row.F), new Bishop(side));
        map.put(Square.of(column, Row.G), new Knight(side));
        map.put(Square.of(column, Row.H), new Rook(side));
    }

    private void setSecondLine(Map<Square, Piece> map, Side side, Column column) {
        for (Row row : Row.values()) {
            map.put(Square.of(column, row), new Pawn(side));
        }
    }
}