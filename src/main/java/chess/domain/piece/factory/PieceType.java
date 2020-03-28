package chess.domain.piece.factory;

import chess.domain.piece.Piece;
import chess.domain.piece.move.*;
import chess.domain.piece.pawn.InitializedPawn;

import java.util.Arrays;
import java.util.List;

public enum PieceType {
    INITIALIZED_PAWN("p", InitializedPawn.class, getCanNotMoveStrategiesForInitializedPawn());

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

    private static List<CanNotMoveStrategy> getCanNotMoveStrategiesForInitializedPawn() {
        return Arrays.asList(
                new IsStayed(),
                new IsNotForward(),
                new InitializedPawnCanNotReach(InitializedPawn.MAX_DISTANCE),
                new InitializedPawnHasHindrance(),
                new IsAttackingSameTeam(),
                new IsDiagonalWithoutAttack()
        );
    }

    public String getName() {
        return name;
    }

    public List<CanNotMoveStrategy> getCanNotMoveStrategies() {
        return canNotMoveStrategies;
    }
}
