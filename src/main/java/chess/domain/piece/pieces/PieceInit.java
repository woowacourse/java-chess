package chess.domain.piece.pieces;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.movable.*;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;

public enum PieceInit {
    A1(PieceType.ROOK,PositionFactory.of("a1"),Color.WHITE, new BlockedMovable(Directions.LINEAR)),
    B1(PieceType.BISHOP,PositionFactory.of("b1"),Color.WHITE, new BlockedMovable(Directions.DIAGONAL)),
    C1(PieceType.KNIGHT,PositionFactory.of("c1"),Color.WHITE, new UnblockedMovable(Directions.KNIGHT)),
    D1(PieceType.QUEEN,PositionFactory.of("d1"),Color.WHITE, new BlockedMovable(Directions.EVERY)),
    E1(PieceType.KING,PositionFactory.of("e1"),Color.WHITE, new UnblockedMovable(Directions.EVERY)),
    F1(PieceType.KNIGHT,PositionFactory.of("f1"),Color.WHITE, new UnblockedMovable(Directions.KNIGHT)),
    G1(PieceType.BISHOP,PositionFactory.of("g1"),Color.WHITE, new BlockedMovable(Directions.DIAGONAL)),
    H1(PieceType.ROOK,PositionFactory.of("h1"),Color.WHITE, new BlockedMovable(Directions.LINEAR)),
    A2(PieceType.PAWN,PositionFactory.of("a2"),Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    B2(PieceType.PAWN,PositionFactory.of("b2"),Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    C2(PieceType.PAWN,PositionFactory.of("c2"),Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    D2(PieceType.PAWN,PositionFactory.of("d2"),Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    E2(PieceType.PAWN,PositionFactory.of("e2"),Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    F2(PieceType.PAWN,PositionFactory.of("f2"),Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    G2(PieceType.PAWN,PositionFactory.of("g2"),Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    H2(PieceType.PAWN,PositionFactory.of("h2"),Color.WHITE, new PawnMovable(Directions.WHITEPAWN)),
    A7(PieceType.PAWN,PositionFactory.of("a7"),Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    B7(PieceType.PAWN,PositionFactory.of("b7"),Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    C7(PieceType.PAWN,PositionFactory.of("c7"),Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    D7(PieceType.PAWN,PositionFactory.of("d7"),Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    E7(PieceType.PAWN,PositionFactory.of("e7"),Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    F7(PieceType.PAWN,PositionFactory.of("f7"),Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    G7(PieceType.PAWN,PositionFactory.of("g7"),Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    H7(PieceType.PAWN,PositionFactory.of("h7"),Color.BLACK, new PawnMovable(Directions.BLACKPAWN)),
    A8(PieceType.ROOK,PositionFactory.of("a8"),Color.BLACK, new BlockedMovable(Directions.LINEAR)),
    B8(PieceType.BISHOP,PositionFactory.of("b8"),Color.BLACK, new BlockedMovable(Directions.DIAGONAL)),
    C8(PieceType.KNIGHT,PositionFactory.of("c8"),Color.BLACK, new UnblockedMovable(Directions.KNIGHT)),
    D8(PieceType.QUEEN,PositionFactory.of("d8"),Color.BLACK, new BlockedMovable(Directions.EVERY)),
    E8(PieceType.KING,PositionFactory.of("e8"),Color.BLACK, new UnblockedMovable(Directions.EVERY)),
    F8(PieceType.KNIGHT,PositionFactory.of("f8"),Color.BLACK, new UnblockedMovable(Directions.KNIGHT)),
    G8(PieceType.BISHOP,PositionFactory.of("g8"),Color.BLACK, new BlockedMovable(Directions.DIAGONAL)),
    H8(PieceType.ROOK,PositionFactory.of("h8"),Color.BLACK, new BlockedMovable(Directions.LINEAR));

    private final PieceType pieceType;
    private final Position position;
    private final Color color;
    private final Movable movable;

    PieceInit(PieceType pieceType, Position position, Color color, Movable movable) {
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
