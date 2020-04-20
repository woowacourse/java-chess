package chess.domain.piece.factory;

import chess.domain.piece.*;
import chess.domain.piece.policy.move.*;
import chess.domain.piece.position.Position;
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
            new Score(1),
            InitializedPawn.class),
    MOVED_PAWN("p",
            movedPawnCanNotMoveStrategies(),
            new ArrayList<>(),
            new Score(1),
            MovedPawn.class),
    ROOK("r",
            rookCanNotMoveStrategies(),
            Arrays.asList(new Column(1), new Column(8)),
            new Score(5),
            Rook.class),
    KNIGHT("n",
            knightCanNotMoveStrategies(),
            Arrays.asList(new Column(2), new Column(7)),
            new Score(2.5),
            Knight.class),
    BISHOP("b",
            bishopCanNotMoveStrategies(),
            Arrays.asList(new Column(3), new Column(6)),
            new Score(3),
            Bishop.class),
    QUEEN("q",
            queenCanNotMoveStrategies(),
            Collections.singletonList(new Column(4)),
            new Score(9),
            Queen.class),
    KING("k",
            kingCanNotMoveStrategies(),
            Collections.singletonList(new Column(5)),
            new Score(0),
            King.class);


    private final String name;
    private final List<CanNotMoveStrategy> canNotMoveStrategies;
    private final List<Column> initialColumns;
    private final Score score;
    private final Class<? extends Piece> type;

    PieceType(String name,
              List<CanNotMoveStrategy> canNotMoveStrategies,
              List<Column> initialColumns,
              Score score,
              Class<? extends Piece> type) {
        this.name = name;
        this.canNotMoveStrategies = canNotMoveStrategies;
        this.initialColumns = initialColumns;
        this.score = score;
        this.type = type;
    }

    public static PieceType valueOf(Class<? extends Piece> type) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.type == type)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 타입을 찾을 수 없습니다"));
    }

    public static PieceType findByInitialColumn(int initialColumn) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.initialColumns.contains(new Column(initialColumn)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d에 해당하는 체스 말을 찾을 수 없습니다.", initialColumn)));
    }

    public static boolean canNotMove(Piece piece, Position to, PiecesState piecesState) {
        List<CanNotMoveStrategy> canNotMoveStrategies = getCanNotMoveStrategiesOf(piece.getClass());
        return canNotMoveStrategies.stream()
                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(piece.getPosition(), to, piecesState));
    }

    public String getName(Team team) {
        return Team.convertName(name, team);
    }

    public Score getScore() {
        return score;
    }

    List<CanNotMoveStrategy> getCanNotMoveStrategies() {
        return canNotMoveStrategies;
    }

    private static List<CanNotMoveStrategy> getCanNotMoveStrategiesOf(Class<? extends Piece> type) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.type == type)
                .map(pieceType -> pieceType.canNotMoveStrategies)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 타입을 찾을 수 없습니다"));
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
