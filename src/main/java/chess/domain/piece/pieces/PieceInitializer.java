package chess.domain.piece.pieces;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.movable.*;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;

public enum PieceInitializer {
    A1(PieceType.ROOK, PositionFactory.of("a1"), new BlockedMovable(Directions.LINEAR)),
    B1(PieceType.KNIGHT, PositionFactory.of("b1"), new UnblockedMovable(Directions.KNIGHT)),
    C1(PieceType.BISHOP, PositionFactory.of("c1"), new BlockedMovable(Directions.DIAGONAL)),
    D1(PieceType.KING, PositionFactory.of("d1"), new UnblockedMovable(Directions.EVERY)),
    E1(PieceType.QUEEN, PositionFactory.of("e1"), new BlockedMovable(Directions.EVERY)),
    F1(PieceType.BISHOP, PositionFactory.of("f1"), new BlockedMovable(Directions.DIAGONAL)),
    G1(PieceType.KNIGHT, PositionFactory.of("g1"), new UnblockedMovable(Directions.KNIGHT)),
    H1(PieceType.ROOK, PositionFactory.of("h1"), new BlockedMovable(Directions.LINEAR)),
    A2(PositionFactory.of("a2")),
    B2(PositionFactory.of("b2")),
    C2(PositionFactory.of("c2")),
    D2(PositionFactory.of("d2")),
    E2(PositionFactory.of("e2")),
    F2(PositionFactory.of("f2")),
    G2(PositionFactory.of("g2")),
    H2(PositionFactory.of("h2")),
    A7(PositionFactory.of("a7")),
    B7(PositionFactory.of("b7")),
    C7(PositionFactory.of("c7")),
    D7(PositionFactory.of("d7")),
    E7(PositionFactory.of("e7")),
    F7(PositionFactory.of("f7")),
    G7(PositionFactory.of("g7")),
    H7(PositionFactory.of("h7")),
    A8(PieceType.ROOK, PositionFactory.of("a8"), new BlockedMovable(Directions.LINEAR)),
    B8(PieceType.KNIGHT, PositionFactory.of("b8"), new UnblockedMovable(Directions.KNIGHT)),
    C8(PieceType.BISHOP, PositionFactory.of("c8"), new BlockedMovable(Directions.DIAGONAL)),
    D8(PieceType.KING, PositionFactory.of("d8"), new UnblockedMovable(Directions.EVERY)),
    E8(PieceType.QUEEN, PositionFactory.of("e8"), new BlockedMovable(Directions.EVERY)),
    F8(PieceType.BISHOP, PositionFactory.of("f8"), new BlockedMovable(Directions.DIAGONAL)),
    G8(PieceType.KNIGHT, PositionFactory.of("g8"), new UnblockedMovable(Directions.KNIGHT)),
    H8(PieceType.ROOK, PositionFactory.of("h8"), new BlockedMovable(Directions.LINEAR));

    private final PieceType pieceType;
    private final Position position;
    private final Color color;
    private final Movable movable;

    PieceInitializer(PieceType pieceType, Position position, Movable movable) {
        this.pieceType = pieceType;
        this.position = position;
        if (position.isHalfBottom()) {
            this.color = Color.WHITE;
            this.movable = movable;
            return;
        }
        this.color = Color.BLACK;
        this.movable = movable;
    }

    PieceInitializer(Position position) {
        this.pieceType = PieceType.PAWN;
        this.position = position;
        if (position.isHalfBottom()) {
            this.color = Color.WHITE;
            this.movable = new PawnMovable(Directions.WHITE_PAWN);
            return;
        }
        this.color = Color.BLACK;
        this.movable = new PawnMovable(Directions.BLACK_PAWN);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public Movable getMovable() {
        return movable;
    }
}
