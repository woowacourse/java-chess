package chess.domain.board;

import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.limited_moving_chess_piece.BlackPawn;
import chess.domain.piece.limited_moving_chess_piece.Knight;
import chess.domain.piece.limited_moving_chess_piece.None;
import chess.domain.piece.limited_moving_chess_piece.WhitePawn;
import chess.domain.piece.linear_moving_chess_piece.Bishop;
import chess.domain.piece.linear_moving_chess_piece.Queen;
import chess.domain.piece.linear_moving_chess_piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardBasicInitializer implements ChessBoardInitializer {

    @Override
    public Map<Position, ChessPiece> initialize(ChessPiece blackKing, ChessPiece whiteKing) {
        Map<Position, ChessPiece> board = new HashMap<>();

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                board.put(new Position(row, column), new None());
            }
        }

        board.put(new Position(Row.EIGHT, Column.A), new Rook(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.B), new Knight(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.C), new Bishop(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.D), new Queen(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.E), blackKing);
        board.put(new Position(Row.EIGHT, Column.F), new Bishop(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.G), new Knight(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.H), new Rook(Color.BLACK));

        board.put(new Position(Row.SEVEN, Column.A), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.B), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.C), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.D), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.E), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.F), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.G), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.H), new BlackPawn());

        board.put(new Position(Row.ONE, Column.A), new Rook(Color.WHITE));
        board.put(new Position(Row.ONE, Column.B), new Knight(Color.WHITE));
        board.put(new Position(Row.ONE, Column.C), new Bishop(Color.WHITE));
        board.put(new Position(Row.ONE, Column.D), new Queen(Color.WHITE));
        board.put(new Position(Row.ONE, Column.E), whiteKing);
        board.put(new Position(Row.ONE, Column.F), new Bishop(Color.WHITE));
        board.put(new Position(Row.ONE, Column.G), new Knight(Color.WHITE));
        board.put(new Position(Row.ONE, Column.H), new Rook(Color.WHITE));

        board.put(new Position(Row.TWO, Column.A), new WhitePawn());
        board.put(new Position(Row.TWO, Column.B), new WhitePawn());
        board.put(new Position(Row.TWO, Column.C), new WhitePawn());
        board.put(new Position(Row.TWO, Column.D), new WhitePawn());
        board.put(new Position(Row.TWO, Column.E), new WhitePawn());
        board.put(new Position(Row.TWO, Column.F), new WhitePawn());
        board.put(new Position(Row.TWO, Column.G), new WhitePawn());
        board.put(new Position(Row.TWO, Column.H), new WhitePawn());

        return board;
    }
}
