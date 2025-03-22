package chess.piece;

import chess.piece.types.*;
import chess.position.Position;

import java.util.List;

import static chess.position.Column.*;

import static chess.position.Row.*;

public enum PieceType {

    BISHOP(List.of(new Position(C, ONE), new Position(F, ONE)),
            List.of(new Position(C, EIGHT), new Position(F, EIGHT)),
            new Bishop()),
    KING(new Position(D, ONE),
            new Position(D, EIGHT),
            new King()),
    KNIGHT(List.of(new Position(B, ONE), new Position(G, ONE)),
            List.of(new Position(B, EIGHT), new Position(G, EIGHT)),
            new Knight()),
    PAWN(List.of(new Position(A, TWO), new Position(B, TWO), new Position(C, TWO), new Position(D, TWO), new Position(E, TWO), new Position(F, TWO), new Position(G, TWO), new Position(H, TWO)),
            List.of(new Position(A, SEVEN), new Position(B, SEVEN), new Position(C, SEVEN), new Position(D, SEVEN), new Position(E, SEVEN), new Position(F, SEVEN), new Position(G, SEVEN), new Position(H, SEVEN)),
            new Pawn()),
    QUEEN(new Position(E, ONE),
            new Position(E, EIGHT),
            new Queen()),
    ROOK(List.of(new Position(A, ONE), new Position(H, ONE)),
            List.of(new Position(A, EIGHT), new Position(H, EIGHT)),
            new Rook()),
    ;

    private final List<Position> whiteInitPositions;
    private final List<Position> blackInitPositions;
    private final Piece piece;

    PieceType(List<Position> whiteInitPositions, List<Position> blackInitPositions, Piece piece) {
        this.whiteInitPositions = whiteInitPositions;
        this.blackInitPositions = blackInitPositions;
        this.piece = piece;
    }

    PieceType(Position whiteInitPosition, Position blackInitPosition, Piece piece) {
        this.whiteInitPositions = List.of(whiteInitPosition);
        this.blackInitPositions = List.of(blackInitPosition);
        this.piece = piece;
    }

    public List<Position> getWhiteInitPositions() {
        return whiteInitPositions;
    }

    public List<Position> getBlackInitPositions() {
        return blackInitPositions;
    }

    public Piece getPiece() {
        return piece;
    }
}
