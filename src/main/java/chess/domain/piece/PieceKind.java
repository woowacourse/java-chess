package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.strategy.*;

public enum PieceKind {

    KING("K", 0, new KingMoveStrategy()),
    QUEEN("Q", 9, new QueenMoveStrategy()),
    KNIGHT("N", 2.5, new KnightMoveStrategy()),
    BISHOP("B", 3,  new BishopMoveStrategy()),
    ROOK("R", 5,  new RookMoveStrategy()),
    PAWN("P", 1, new PawnMoveStrategy()),
    VOID(".", 0, new VoidMoveStrategy());

    private final String symbol;
    private final double point;
    private final MoveStrategy moveStrategy;

    PieceKind(String symbol, double point, MoveStrategy moveStrategy) {
        this.symbol = symbol;
        this.point = point;
        this.moveStrategy = moveStrategy;
    }


    public String getName(final PieceColor pieceColor) {
        if (pieceColor == PieceColor.WHITE) {
            return symbol.toLowerCase();
        }

        return symbol;
    }

    public double point() {
        return this.point;
    }

    public void movable(final Position source, final Position target) {
        this.moveStrategy.move(source, target);
    }
}
