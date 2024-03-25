package chess.domain.chesspiece;

import chess.domain.chesspiece.movestrategy.BishopMoveStrategy;
import chess.domain.chesspiece.movestrategy.EmptyMoveStrategy;
import chess.domain.chesspiece.movestrategy.KingMoveStrategy;
import chess.domain.chesspiece.movestrategy.KnightMoveStrategy;
import chess.domain.chesspiece.movestrategy.MoveStrategy;
import chess.domain.chesspiece.movestrategy.PawnMoveStrategy;
import chess.domain.chesspiece.movestrategy.QueenMoveStrategy;
import chess.domain.chesspiece.movestrategy.RookMoveStrategy;
import java.util.function.Supplier;

public enum ChessPieceType {

    NONE(EmptyMoveStrategy::new),
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

    public static boolean isChessPieceTypeNone(ChessPieceType chessPieceType) {
        return chessPieceType.equals(NONE);
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategySupplier.get();
    }
}
