package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.List;

public class Board {

    private final List<Piece> pieces = List.of(
            new King(Color.WHITE, new Position(Column.E, Row.ONE)),
            new Queen(Color.WHITE, new Position(Column.D, Row.ONE)),
            new Knight(Color.WHITE, new Position(Column.B, Row.ONE)),
            new Knight(Color.WHITE, new Position(Column.G, Row.ONE)),
            new Bishop(Color.WHITE, new Position(Column.C, Row.ONE)),
            new Bishop(Color.WHITE, new Position(Column.F, Row.ONE)),
            new Rook(Color.WHITE, new Position(Column.A, Row.ONE)),
            new Rook(Color.WHITE, new Position(Column.H, Row.ONE)),
            new Pawn(Color.WHITE, new Position(Column.A, Row.TWO)),
            new Pawn(Color.WHITE, new Position(Column.B, Row.TWO)),
            new Pawn(Color.WHITE, new Position(Column.C, Row.TWO)),
            new Pawn(Color.WHITE, new Position(Column.D, Row.TWO)),
            new Pawn(Color.WHITE, new Position(Column.E, Row.TWO)),
            new Pawn(Color.WHITE, new Position(Column.F, Row.TWO)),
            new Pawn(Color.WHITE, new Position(Column.G, Row.TWO)),
            new Pawn(Color.WHITE, new Position(Column.H, Row.TWO)),
            new King(Color.BLACK, new Position(Column.E, Row.EIGHT)),
            new Queen(Color.BLACK, new Position(Column.D, Row.EIGHT)),
            new Knight(Color.BLACK, new Position(Column.B, Row.EIGHT)),
            new Knight(Color.BLACK, new Position(Column.G, Row.EIGHT)),
            new Bishop(Color.BLACK, new Position(Column.C, Row.EIGHT)),
            new Bishop(Color.BLACK, new Position(Column.F, Row.EIGHT)),
            new Rook(Color.BLACK, new Position(Column.A, Row.EIGHT)),
            new Rook(Color.BLACK, new Position(Column.H, Row.EIGHT)),
            new Pawn(Color.BLACK, new Position(Column.A, Row.SEVEN)),
            new Pawn(Color.BLACK, new Position(Column.B, Row.SEVEN)),
            new Pawn(Color.BLACK, new Position(Column.C, Row.SEVEN)),
            new Pawn(Color.BLACK, new Position(Column.D, Row.SEVEN)),
            new Pawn(Color.BLACK, new Position(Column.E, Row.SEVEN)),
            new Pawn(Color.BLACK, new Position(Column.F, Row.SEVEN)),
            new Pawn(Color.BLACK, new Position(Column.G, Row.SEVEN)),
            new Pawn(Color.BLACK, new Position(Column.H, Row.SEVEN))
    );

    public boolean existPieceIn(Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position));
    }

    public Piece getPieceIn(Color nowColor, Position start) {
        return pieces.stream()
                .filter(piece -> piece.isSameTeam(nowColor))
                .filter(piece -> piece.isSamePosition(start))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
