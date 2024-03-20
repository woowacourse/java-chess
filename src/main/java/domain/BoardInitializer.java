package domain;

import domain.position.File;
import domain.position.Position;
import domain.position.UnitVector;
import domain.strategy.ContinuousMoveStrategy;
import domain.strategy.KnightMoveStrategy;
import domain.strategy.MoveStrategy;
import domain.strategy.PawnMoveStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static domain.PieceType.*;
import static domain.position.File.*;
import static domain.position.Rank.*;

public class BoardInitializer {
    private static final int MAXIMUM_MOVE_BOUND = 8;
    private static final int KING_MAXIMUM_MOVE_BOUND = 1;

    private static final MoveStrategy whitePawnStrategy = new PawnMoveStrategy(TeamColor.WHITE);
    private static final MoveStrategy blackPawnStrategy = new PawnMoveStrategy(TeamColor.BLACK);
    private static final MoveStrategy knightStrategy = new KnightMoveStrategy();
    private static final MoveStrategy rookStrategy = new ContinuousMoveStrategy(UnitVector.orthogonalVectors(), MAXIMUM_MOVE_BOUND);
    private static final MoveStrategy bishopStrategy = new ContinuousMoveStrategy(UnitVector.diagonalVectors(), MAXIMUM_MOVE_BOUND);
    private static final MoveStrategy queenStrategy = new ContinuousMoveStrategy(UnitVector.omnidirectionalVectors(), MAXIMUM_MOVE_BOUND);
    private static final MoveStrategy kingStrategy = new ContinuousMoveStrategy(UnitVector.omnidirectionalVectors(), KING_MAXIMUM_MOVE_BOUND);
    private static final Map<PieceType, List<Position>> pieceInitialPositions = Map.ofEntries(
            Map.entry(BLACK_PAWN, Arrays.stream(File.values()).map(file -> new Position(file, SEVEN)).toList()),
            Map.entry(BLACK_ROOK, List.of(new Position(A, EIGHT), new Position(H, EIGHT))),
            Map.entry(BLACK_KNIGHT, List.of(new Position(B, EIGHT), new Position(G, EIGHT))),
            Map.entry(BLACK_BISHOP, List.of(new Position(C, EIGHT), new Position(F, EIGHT))),
            Map.entry(BLACK_QUEEN, List.of(new Position(D, EIGHT))),
            Map.entry(BLACK_KING, List.of(new Position(E, EIGHT))),
            Map.entry(WHITE_PAWN, Arrays.stream(File.values()).map(file -> new Position(file, TWO)).toList()),
            Map.entry(WHITE_ROOK, List.of(new Position(A, ONE), new Position(H, ONE))),
            Map.entry(WHITE_KNIGHT, List.of(new Position(B, ONE), new Position(G, ONE))),
            Map.entry(WHITE_BISHOP, List.of(new Position(C, ONE), new Position(F, ONE))),
            Map.entry(WHITE_QUEEN, List.of(new Position(D, ONE))),
            Map.entry(WHITE_KING, List.of(new Position(E, ONE)))
    );
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

    public static Board init() {
        Map<Position, Piece> positions = new HashMap<>();
        pieceInitialPositions.forEach(((pieceType, position) -> {
            position.forEach(p -> positions.put(p, new Piece(pieceType, moveStrategies.get(pieceType))));
        }));

        return new Board(positions);
    }
}
