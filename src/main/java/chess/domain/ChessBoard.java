package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, ChessPiece> board = new HashMap<>();

    public ChessBoard() {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                board.put(new Position(row, column), null);
            }
        }

        board.put(new Position(Row.EIGHT, Column.A), new Rook());
        board.put(new Position(Row.EIGHT, Column.B), new Knight());
        board.put(new Position(Row.EIGHT, Column.C), new Bishop());
        board.put(new Position(Row.EIGHT, Column.D), new Queen());
        board.put(new Position(Row.EIGHT, Column.E), new King());
        board.put(new Position(Row.EIGHT, Column.F), new Bishop());
        board.put(new Position(Row.EIGHT, Column.G), new Knight());
        board.put(new Position(Row.EIGHT, Column.H), new Rook());

        board.put(new Position(Row.SEVEN, Column.A), new Pawn());
        board.put(new Position(Row.SEVEN, Column.B), new Pawn());
        board.put(new Position(Row.SEVEN, Column.C), new Pawn());
        board.put(new Position(Row.SEVEN, Column.D), new Pawn());
        board.put(new Position(Row.SEVEN, Column.E), new Pawn());
        board.put(new Position(Row.SEVEN, Column.F), new Pawn());
        board.put(new Position(Row.SEVEN, Column.G), new Pawn());
        board.put(new Position(Row.SEVEN, Column.H), new Pawn());

        board.put(new Position(Row.ONE, Column.A), new Rook());
        board.put(new Position(Row.ONE, Column.B), new Knight());
        board.put(new Position(Row.ONE, Column.C), new Bishop());
        board.put(new Position(Row.ONE, Column.D), new Queen());
        board.put(new Position(Row.ONE, Column.E), new King());
        board.put(new Position(Row.ONE, Column.F), new Bishop());
        board.put(new Position(Row.ONE, Column.G), new Knight());
        board.put(new Position(Row.ONE, Column.H), new Rook());

        board.put(new Position(Row.TWO, Column.A), new Pawn());
        board.put(new Position(Row.TWO, Column.B), new Pawn());
        board.put(new Position(Row.TWO, Column.C), new Pawn());
        board.put(new Position(Row.TWO, Column.D), new Pawn());
        board.put(new Position(Row.TWO, Column.E), new Pawn());
        board.put(new Position(Row.TWO, Column.F), new Pawn());
        board.put(new Position(Row.TWO, Column.G), new Pawn());
        board.put(new Position(Row.TWO, Column.H), new Pawn());
    }


}
