package chess.domain.piece.pieces;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.movable.*;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;

public enum PieceInitializer {
    A1(PieceType.ROOK, PositionFactory.of("a1"), Color.WHITE, new BlockedMovable(Directions.LINEAR)),
    B1(PieceType.KNIGHT, PositionFactory.of("b1"), Color.WHITE, new UnblockedMovable(Directions.KNIGHT)),
    C1(PieceType.BISHOP, PositionFactory.of("c1"), Color.WHITE, new BlockedMovable(Directions.DIAGONAL)),
    D1(PieceType.KING, PositionFactory.of("d1"), Color.WHITE, new UnblockedMovable(Directions.EVERY)),
    E1(PieceType.QUEEN, PositionFactory.of("e1"), Color.WHITE, new BlockedMovable(Directions.EVERY)),
    F1(PieceType.BISHOP, PositionFactory.of("f1"), Color.WHITE, new BlockedMovable(Directions.DIAGONAL)),
    G1(PieceType.KNIGHT, PositionFactory.of("g1"), Color.WHITE, new UnblockedMovable(Directions.KNIGHT)),
    H1(PieceType.ROOK, PositionFactory.of("h1"), Color.WHITE, new BlockedMovable(Directions.LINEAR)),
    A2(PieceType.PAWN, PositionFactory.of("a2"), Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    B2(PieceType.PAWN, PositionFactory.of("b2"), Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    C2(PieceType.PAWN, PositionFactory.of("c2"), Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    D2(PieceType.PAWN, PositionFactory.of("d2"), Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    E2(PieceType.PAWN, PositionFactory.of("e2"), Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    F2(PieceType.PAWN, PositionFactory.of("f2"), Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    G2(PieceType.PAWN, PositionFactory.of("g2"), Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    H2(PieceType.PAWN, PositionFactory.of("h2"), Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    A7(PieceType.PAWN, PositionFactory.of("a7"), Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    B7(PieceType.PAWN, PositionFactory.of("b7"), Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    C7(PieceType.PAWN, PositionFactory.of("c7"), Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    D7(PieceType.PAWN, PositionFactory.of("d7"), Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    E7(PieceType.PAWN, PositionFactory.of("e7"), Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    F7(PieceType.PAWN, PositionFactory.of("f7"), Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    G7(PieceType.PAWN, PositionFactory.of("g7"), Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    H7(PieceType.PAWN, PositionFactory.of("h7"), Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    A8(PieceType.ROOK, PositionFactory.of("a8"), Color.BLACK, new BlockedMovable(Directions.LINEAR)),
    B8(PieceType.KNIGHT, PositionFactory.of("b8"), Color.BLACK, new UnblockedMovable(Directions.KNIGHT)),
    C8(PieceType.BISHOP, PositionFactory.of("c8"), Color.BLACK, new BlockedMovable(Directions.DIAGONAL)),
    D8(PieceType.KING, PositionFactory.of("d8"), Color.BLACK, new UnblockedMovable(Directions.EVERY)),
    E8(PieceType.QUEEN, PositionFactory.of("e8"), Color.BLACK, new BlockedMovable(Directions.EVERY)),
    F8(PieceType.BISHOP, PositionFactory.of("f8"), Color.BLACK, new BlockedMovable(Directions.DIAGONAL)),
    G8(PieceType.KNIGHT, PositionFactory.of("g8"), Color.BLACK, new UnblockedMovable(Directions.KNIGHT)),
    H8(PieceType.ROOK, PositionFactory.of("h8"), Color.BLACK, new BlockedMovable(Directions.LINEAR));

    private final PieceType pieceType;
    private final Position position;
    private final Color color;
    private final Movable movable;

    PieceInitializer(PieceType pieceType, Position position, Color color, Movable movable) {
        this.pieceType = pieceType;
        this.position = position;
        this.color = color;
        this.movable = movable;
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