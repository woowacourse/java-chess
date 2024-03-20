package chess.chesspiece;

import chess.chesspiece.movestrategy.BishopMoveStrategy;
import chess.chesspiece.movestrategy.KingMoveStrategy;
import chess.chesspiece.movestrategy.KnightMoveStrategy;
import chess.chesspiece.movestrategy.MoveStrategy;
import chess.chesspiece.movestrategy.PawnMoveStrategy;
import chess.chesspiece.movestrategy.QueenMoveStrategy;
import chess.chesspiece.movestrategy.RookMoveStrategy;
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
