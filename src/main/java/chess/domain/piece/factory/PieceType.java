package chess.domain.piece.factory;

import chess.domain.piece.Piece;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.move.*;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.pawn.MovedPawn;
import chess.domain.piece.rook.Rook;

import java.util.Arrays;
import java.util.List;

public enum PieceType {
    INITIALIZED_PAWN("p", InitializedPawn.class, initializedPawnCanNotMoveStrategies()),
    RUNNING_PAWN("p", MovedPawn.class, movedPawnCanNotMoveStrategies()),
    ROOK("r", Rook.class, rookCanNotMoveStrategies()),
    BISHOP("b", Bishop.class, bishopCanNotMoveStrategies()),
    ;

    private static List<CanNotMoveStrategy> bishopCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new IsPerpendicular()
        );
    }

    private static List<CanNotMoveStrategy> rookCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new IsHeadingDiagonal()
        );
    }

    private static List<CanNotMoveStrategy> movedPawnCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new IsNotForward(),
                new CanNotReach(MovedPawn.MAX_DISTANCE),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new PawnIsDiagonalWithoutAttack()

        );
    }

    private final String name;
    private final Class<? extends Piece> type;
    private final List<CanNotMoveStrategy> canNotMoveStrategies;

    PieceType(String name, Class<? extends Piece> type, List<CanNotMoveStrategy> canNotMoveStrategies) {
        this.name = name;
        this.type = type;
        this.canNotMoveStrategies = canNotMoveStrategies;
    }

    public static PieceType valueOf(Class<? extends Piece> type) {
        for (PieceType pieceType : values()) {
            if (pieceType.type == type) {
                return pieceType;
            }
        }
        throw new IllegalArgumentException("해당하는 PieceType을 찾을 수 없습니다.");
    }

    private static List<CanNotMoveStrategy> initializedPawnCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new IsNotForward(),
                new CanNotReach(InitializedPawn.MAX_DISTANCE),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new PawnIsDiagonalWithoutAttack()
        );
    }

    public String getName() {
        return name;
    }

    public List<CanNotMoveStrategy> getCanNotMoveStrategies() {
        return canNotMoveStrategies;
    }
}
