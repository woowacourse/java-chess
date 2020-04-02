package chess.domain.piece.factory;

import chess.domain.piece.Piece;
import chess.domain.piece.bishop.Bishop;

import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.move.*;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.pawn.MovedPawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import chess.domain.piece.score.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

enum PieceType {
    INITIALIZED_PAWN("p",
            InitializedPawn.class,
            initializedPawnCanNotMoveStrategies(),
            new NullList(),
            new Score(1)),
    RUNNING_PAWN("p",
            MovedPawn.class,
            movedPawnCanNotMoveStrategies(),
            new NullList(),
            new Score(1)),
    ROOK("r",
            Rook.class,
            rookCanNotMoveStrategies(),
            Arrays.asList(new InitialColumn(1), new InitialColumn(8)),
            new Score(5)),
    KNIGHT("n",
            Knight.class,
            knightCanNotMoveStrategies(),
            Arrays.asList(new InitialColumn(2), new InitialColumn(7)),
            new Score(2.5)),
    BISHOP("b",
            Bishop.class,
            bishopCanNotMoveStrategies(),
            Arrays.asList(new InitialColumn(3), new InitialColumn(6)),
            new Score(3)),
    QUEEN("q",
            Queen.class,
            queenCanNotMoveStrategies(),
            Collections.singletonList(new InitialColumn(4)),
            new Score(3)),
    KING("k",
            King.class,
            kingCanNotMoveStrategies(),
            Collections.singletonList(new InitialColumn(5)),
            new Score(0));


    private final String name;
    private final Class<? extends Piece> type;
    private final List<CanNotMoveStrategy> canNotMoveStrategies;
    private final List<InitialColumn> initialColumns;
    private final Score score;

    PieceType(String name, Class<? extends Piece> type,
              List<CanNotMoveStrategy> canNotMoveStrategies,
              List<InitialColumn> initialColumns,
              Score score) {
        this.name = name;
        this.type = type;
        this.canNotMoveStrategies = canNotMoveStrategies;
        this.initialColumns = initialColumns;
        this.score = score;
    }

    static PieceType valueOf(Class<? extends Piece> type) {
        for (PieceType pieceType : values()) {
            if (pieceType.type == type) {
                return pieceType;
            }
        }
        throw new IllegalArgumentException("해당하는 PieceType을 찾을 수 없습니다.");
    }

    static Class<? extends Piece> findTypeByInitialColumn(int initialColumn) {
        for (PieceType piece : values()) {
            if (piece.initialColumns.contains(new InitialColumn(initialColumn))) {
                return piece.type;
            }
        }

        throw new IllegalArgumentException(String.format("%d에 해당하는 체스 말을 찾을 수 없습니다.", initialColumn));
    }

    String getName() {
        return name;
    }

    List<CanNotMoveStrategy> getCanNotMoveStrategies() {
        return canNotMoveStrategies;
    }

    private static List<CanNotMoveStrategy> initializedPawnCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new IsNotHeadingForward(),
                new CanNotReach(InitializedPawn.MAX_DISTANCE),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new PawnIsDiagonalWithoutAttack()
        );
    }

    private static List<CanNotMoveStrategy> queenCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new IsNotHeadingStraightDirection()

        );
    }

    private static List<CanNotMoveStrategy> bishopCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new IsNotHeadingStraightDiagonalDirection()
        );
    }

    private static List<CanNotMoveStrategy> rookCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new IsHeadingDiagonalDirection()
        );
    }

    private static List<CanNotMoveStrategy> movedPawnCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new IsNotHeadingForward(),
                new CanNotReach(MovedPawn.MAX_DISTANCE),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new PawnIsDiagonalWithoutAttack()

        );
    }

    private static List<CanNotMoveStrategy> kingCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new CanNotReach(King.MAX_DISTANCE),
                new IsAttackingSameTeam()
        );
    }

    private static List<CanNotMoveStrategy> knightCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new CanNotReach(Knight.MAX_DISTANCE),
                new IsHeadingStraightDirection()
        );
    }

    public Score getScore() {
        return score;
    }

    private static class NullList extends ArrayList {
        @Override
        public boolean contains(Object o) {
            return false;
        }
    }
}
