package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MovingExecutorFactory {
    private static final Map<PieceType, Function<Piece, MovingExecutor>> MOVING_EXECUTOR_CACHE = new HashMap<>();

    static {
        MOVING_EXECUTOR_CACHE.put(PieceType.PAWN, PawnMovingExecutor::new);
        MOVING_EXECUTOR_CACHE.put(PieceType.KNIGHT, KnightMovingExecutor::new);
        MOVING_EXECUTOR_CACHE.put(PieceType.ROOK, DefaultMovingExecutor::new);
        MOVING_EXECUTOR_CACHE.put(PieceType.BISHOP, DefaultMovingExecutor::new);
        MOVING_EXECUTOR_CACHE.put(PieceType.QUEEN, DefaultMovingExecutor::new);
        MOVING_EXECUTOR_CACHE.put(PieceType.KING, DefaultMovingExecutor::new);
    }

    public static MovingExecutor from(Piece piece) {
        return MOVING_EXECUTOR_CACHE.get(piece.getPieceType()).apply(piece);
    }
}
