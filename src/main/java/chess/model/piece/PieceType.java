package chess.model.piece;

import chess.model.element.Color;
import chess.model.piece.types.*;
import chess.model.position.Position;

import java.util.List;

import static chess.model.position.Column.*;

import static chess.model.position.Row.*;

public enum PieceType {

    BISHOP(List.of(new Position(C, ONE), new Position(F, ONE)),
            List.of(new Position(C, EIGHT), new Position(F, EIGHT)),
            new Bishop(Color.BLACK),
            new Bishop(Color.WHITE)),
    KING(new Position(D, ONE),
            new Position(D, EIGHT),
            new King(Color.BLACK),
            new King(Color.WHITE)),
    KNIGHT(List.of(new Position(B, ONE), new Position(G, ONE)),
            List.of(new Position(B, EIGHT), new Position(G, EIGHT)),
            new Knight(Color.BLACK),
            new Knight(Color.WHITE)),
    PAWN(List.of(new Position(A, TWO), new Position(B, TWO), new Position(C, TWO), new Position(D, TWO), new Position(E, TWO), new Position(F, TWO), new Position(G, TWO), new Position(H, TWO)),
            List.of(new Position(A, SEVEN), new Position(B, SEVEN), new Position(C, SEVEN), new Position(D, SEVEN), new Position(E, SEVEN), new Position(F, SEVEN), new Position(G, SEVEN), new Position(H, SEVEN)),
            new Pawn(Color.BLACK),
            new Pawn(Color.WHITE)),
    QUEEN(new Position(E, ONE),
            new Position(E, EIGHT),
            new Queen(Color.BLACK),
            new Queen(Color.WHITE)),
    ROOK(List.of(new Position(A, ONE), new Position(H, ONE)),
            List.of(new Position(A, EIGHT), new Position(H, EIGHT)),
            new Rook(Color.BLACK),
            new Rook(Color.WHITE)),
    ;

    private final List<Position> whiteInitPositions;
    private final List<Position> blackInitPositions;
    private final Piece pieceByBlack;
    private final Piece pieceByWhite;

    PieceType(List<Position> whiteInitPositions, List<Position> blackInitPositions, Piece pieceByBlack, Piece pieceByWhite) {
        this.whiteInitPositions = whiteInitPositions;
        this.blackInitPositions = blackInitPositions;
        this.pieceByBlack = pieceByBlack;
        this.pieceByWhite = pieceByWhite;
    }

    PieceType(Position whiteInitPosition, Position blackInitPosition, Piece pieceByBlack, Piece pieceByWhite) {
        this.whiteInitPositions = List.of(whiteInitPosition);
        this.blackInitPositions = List.of(blackInitPosition);
        this.pieceByBlack = pieceByBlack;
        this.pieceByWhite = pieceByWhite;
    }

    public List<Position> getInitPositionsByColor(final Color color) {
        if (color.isWhite()) {
            return getWhiteInitPositions();
        }
        return getBlackInitPositions();
    }

    private List<Position> getWhiteInitPositions() {
        return whiteInitPositions;
    }

    private List<Position> getBlackInitPositions() {
        return blackInitPositions;
    }

    public Piece getPieceByColor(Color color) {
        if (color.isWhite()) {
            return getPieceByWhite();
        }
        return getPieceByBlack();
    }

    public Piece getPieceByBlack() {
        return pieceByBlack;
    }

    public Piece getPieceByWhite() {
        return pieceByWhite;
    }
}
