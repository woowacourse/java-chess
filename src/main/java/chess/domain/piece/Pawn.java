package chess.domain.piece;

import chess.domain.movement.Movements;
import chess.domain.movement.UnitMovement;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.TerminalPosition;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pawn extends Piece {
    private static final Rank BLACK_INIT_RANK = Rank.SEVENTH;
    private static final Rank WHITE_INIT_RANK = Rank.SECOND;
    private static final int MAX_INIT_PASS_COUNT = 2;
    private static final int MAX_NOT_INIT_PASS_COUNT = 1;
    private static final int MAX_ATTACK_MOVE_COUNT = 1;
    private static final Movements BLACK_MOVEMENTS = new Movements(
            Set.of(UnitMovement.DOWN),
            Set.of(UnitMovement.LEFT_DOWN, UnitMovement.RIGHT_DOWN));
    private static final Movements WHITE_MOVEMENTS = new Movements(
            Set.of(UnitMovement.UP),
            Set.of(UnitMovement.LEFT_UP, UnitMovement.RIGHT_UP));

    private static final Map<Color, Pawn> PAWN_POOL = Map.of(
            Color.BLACK, new Pawn(Color.BLACK, BLACK_MOVEMENTS),
            Color.WHITE, new Pawn(Color.WHITE, WHITE_MOVEMENTS));

    private Pawn(Color color, Movements movements) {
        super(color, movements);
    }

    public static Pawn from(Color color) {
        return PAWN_POOL.get(color);
    }

    @Override
    public List<Position> findPassPathTaken(TerminalPosition terminalPosition) {
        return movements.findPassPathTaken(terminalPosition, maxPassMoveCount(terminalPosition));
    }

    protected int maxPassMoveCount(TerminalPosition terminalPosition) {
        Position startPosition = terminalPosition.getStart();

        if (isBlackInitRank(startPosition) || isWhiteInitRank(startPosition)) {
            return MAX_INIT_PASS_COUNT;
        }

        return MAX_NOT_INIT_PASS_COUNT;
    }

    private boolean isBlackInitRank(Position position) {
        return isColor(Color.BLACK) && position.isRank(BLACK_INIT_RANK);
    }

    private boolean isWhiteInitRank(Position position) {
        return isColor(Color.WHITE) && position.isRank(WHITE_INIT_RANK);
    }

    @Override
    public List<Position> findAttackPathTaken(TerminalPosition terminalPosition) {
        return movements.findAttackPathTaken(terminalPosition, MAX_ATTACK_MOVE_COUNT);
    }
}
