package chess.chessPiece;

import java.util.function.Supplier;

public enum ChessPieceType {

    KING(KingMoveStrategy::new),
    QUEEN(QueenMoveStrategy::new),
    BISHOP(BishopMoveStrategy::new),
    KNIGHT(KnightMoveStrategy::new),
    ROOK(RookMoveStrategy::new),
    PAWN(PawnMoveStrategy::new);

    private final Supplier<MoveStrategy> moveStrategySupplier;

    ChessPieceType(Supplier<MoveStrategy> moveStrategySupplier) {
        this.moveStrategySupplier = moveStrategySupplier;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategySupplier.get();
    }
}
