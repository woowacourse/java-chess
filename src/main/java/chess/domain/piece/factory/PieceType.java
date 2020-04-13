package chess.domain.piece.factory;

import chess.domain.piece.InitializedPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.MovedPawn;
import chess.domain.piece.policy.move.*;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PieceType {
    INITIALIZED_PAWN("p",
            initializedPawnCanNotMoveStrategies(),
            new ArrayList<>(),
            new Score(1)),
    MOVED_PAWN("p",
            movedPawnCanNotMoveStrategies(),
            new ArrayList<>(),
            new Score(1)),
    ROOK("r",
            rookCanNotMoveStrategies(),
            Arrays.asList(new InitialColumn(1), new InitialColumn(8)),
            new Score(5)),
    KNIGHT("n",
            knightCanNotMoveStrategies(),
            Arrays.asList(new InitialColumn(2), new InitialColumn(7)),
            new Score(2.5)),
    BISHOP("b",
            bishopCanNotMoveStrategies(),
            Arrays.asList(new InitialColumn(3), new InitialColumn(6)),
            new Score(3)),
    QUEEN("q",
            queenCanNotMoveStrategies(),
            Collections.singletonList(new InitialColumn(4)),
            new Score(9)),
    KING("k",
            kingCanNotMoveStrategies(),
            Collections.singletonList(new InitialColumn(5)),
            new Score(0));


    private final String name;
    private final List<CanNotMoveStrategy> canNotMoveStrategies;
    private final List<InitialColumn> initialColumns;
    private final Score score;

    PieceType(String name,
              List<CanNotMoveStrategy> canNotMoveStrategies,
              List<InitialColumn> initialColumns,
              Score score) {
        this.name = name;
        this.canNotMoveStrategies = canNotMoveStrategies;
        this.initialColumns = initialColumns;
        this.score = score;
    }

    static PieceType findByInitialColumn(int initialColumn) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.initialColumns.contains(new InitialColumn(initialColumn)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d에 해당하는 체스 말을 찾을 수 없습니다.", initialColumn)));
    }

    public String getName(Team team) {
        return Team.convertName(name, team);
    }

    List<CanNotMoveStrategy> getCanNotMoveStrategies() {
        return canNotMoveStrategies;
    }

    Score getScore() {
        return score;
    }

    private static List<CanNotMoveStrategy> initializedPawnCanNotMoveStrategies() {
        return Arrays.asList(
                new IsStayed(),
                new IsNotHeadingForward(),
                new CanNotReach(InitializedPawn.MAX_DISTANCE),
                new HasHindrance(),
                new IsAttackingSameTeam(),
                new PawnIsDiagonalWithoutAttack(),
                new PawnIsVerticalWithAttack()
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
                new PawnIsDiagonalWithoutAttack(),
                new PawnIsVerticalWithAttack()
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
                new IsHeadingStraightDirection(),
                new IsAttackingSameTeam()
        );
    }
}
