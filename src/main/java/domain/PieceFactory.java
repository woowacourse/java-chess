package domain;

import domain.piece.CommonMovementDirection;
import domain.strategy.ContinuousMoveStrategy;
import domain.strategy.KnightMoveStrategy;
import domain.strategy.MoveStrategy;
import domain.strategy.PawnMoveStrategy;

import java.util.Map;

import static domain.PieceType.*;

public class PieceFactory {
    private static final int MAXIMUM_MOVE_BOUND = 8;
    private static final int KING_MAXIMUM_MOVE_BOUND = 1;
    private static final MoveStrategy whitePawnStrategy = new PawnMoveStrategy(TeamColor.WHITE);
    private static final MoveStrategy blackPawnStrategy = new PawnMoveStrategy(TeamColor.BLACK);
    private static final MoveStrategy knightStrategy = new KnightMoveStrategy();
    private static final MoveStrategy rookStrategy = new ContinuousMoveStrategy(CommonMovementDirection.orthogonalVectors(), MAXIMUM_MOVE_BOUND);
    private static final MoveStrategy bishopStrategy = new ContinuousMoveStrategy(CommonMovementDirection.diagonalVectors(), MAXIMUM_MOVE_BOUND);
    private static final MoveStrategy queenStrategy = new ContinuousMoveStrategy(CommonMovementDirection.omnidirectionalVectors(), MAXIMUM_MOVE_BOUND);
    private static final MoveStrategy kingStrategy = new ContinuousMoveStrategy(CommonMovementDirection.omnidirectionalVectors(), KING_MAXIMUM_MOVE_BOUND);

    private static final Map<PieceType, MoveStrategy> moveStrategies = Map.ofEntries(
            Map.entry(BLACK_PAWN, blackPawnStrategy),
            Map.entry(BLACK_ROOK, rookStrategy),
            Map.entry(BLACK_KNIGHT, knightStrategy),
            Map.entry(BLACK_BISHOP, bishopStrategy),
            Map.entry(BLACK_QUEEN, queenStrategy),
            Map.entry(BLACK_KING, kingStrategy),
            Map.entry(WHITE_PAWN, whitePawnStrategy),
            Map.entry(WHITE_ROOK, rookStrategy),
            Map.entry(WHITE_KNIGHT, knightStrategy),
            Map.entry(WHITE_BISHOP, bishopStrategy),
            Map.entry(WHITE_QUEEN, queenStrategy),
            Map.entry(WHITE_KING, kingStrategy)
    );

    public static Piece create(PieceType pieceType) {
        return new Piece(pieceType, moveStrategies.get(pieceType));
    }
}
