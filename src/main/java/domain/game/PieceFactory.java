package domain.game;

import domain.position.UnitVector;
import domain.strategy.BlackPawnMoveStrategy;
import domain.strategy.ContinuousMoveStrategy;
import domain.strategy.KnightMoveStrategy;
import domain.strategy.MoveStrategy;
import domain.strategy.WhitePawnMoveStrategy;
import java.util.Map;

import static domain.game.PieceType.*;

public class PieceFactory {
    private PieceFactory() {
    }

    private static final int MAXIMUM_MOVE_BOUND = 8;
    private static final int KING_MAXIMUM_MOVE_BOUND = 1;
    private static final MoveStrategy whitePawnStrategy = new WhitePawnMoveStrategy();
    private static final MoveStrategy blackPawnStrategy = new BlackPawnMoveStrategy();
    private static final MoveStrategy knightStrategy = new KnightMoveStrategy();
    private static final MoveStrategy rookStrategy = new ContinuousMoveStrategy(UnitVector.orthogonalVectors(), MAXIMUM_MOVE_BOUND);
    private static final MoveStrategy bishopStrategy = new ContinuousMoveStrategy(UnitVector.diagonalVectors(), MAXIMUM_MOVE_BOUND);
    private static final MoveStrategy queenStrategy = new ContinuousMoveStrategy(UnitVector.omnidirectionalVectors(), MAXIMUM_MOVE_BOUND);
    private static final MoveStrategy kingStrategy = new ContinuousMoveStrategy(UnitVector.omnidirectionalVectors(), KING_MAXIMUM_MOVE_BOUND);

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
