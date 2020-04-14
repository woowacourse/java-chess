package chess.domain.piece.implementation.Strategy;

import chess.domain.board.BoardSituation;
import chess.domain.direction.MovingDirection;
import chess.domain.piece.MoveStrategy;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class PawnStrategy implements MoveStrategy {

    private static final EnumMap<Team, MovingDirection> MOVING_DIRECTION_BY_TEAM = new EnumMap<>(Team.class);
    private static final EnumMap<Team, List<MovingDirection>> ATTACK_DIRECTION_BY_TEAM = new EnumMap<>(Team.class);
    private static final EnumMap<Team, Rank> START_RANK_BY_TEAM = new EnumMap<>(Team.class);

    static {
        MOVING_DIRECTION_BY_TEAM.put(Team.WHITE, MovingDirection.NORTH);
        MOVING_DIRECTION_BY_TEAM.put(Team.BLACK, MovingDirection.SOUTH);

        List<MovingDirection> whiteAttacks = Arrays.asList(MovingDirection.NORTH_EAST, MovingDirection.NORTH_WEST);
        List<MovingDirection> blackAttacks = Arrays.asList(MovingDirection.SOUTH_EAST, MovingDirection.SOUTH_WEST);
        ATTACK_DIRECTION_BY_TEAM.put(Team.WHITE, whiteAttacks);
        ATTACK_DIRECTION_BY_TEAM.put(Team.BLACK, blackAttacks);

        START_RANK_BY_TEAM.put(Team.WHITE, Rank.TWO);
        START_RANK_BY_TEAM.put(Team.BLACK, Rank.SEVEN);
    }

    private PawnMoveStrategy pawnMoveStrategy;
    private PawnAttackStrategy pawnAttackStrategy;

    public PawnStrategy(Team team) {
        this.pawnMoveStrategy = new PawnMoveStrategy(MOVING_DIRECTION_BY_TEAM.get(team), START_RANK_BY_TEAM.get(team));
        this.pawnAttackStrategy = new PawnAttackStrategy(ATTACK_DIRECTION_BY_TEAM.get(team), team);
    }

    @Override
    public List<Position> getMovablePositions(Position source, BoardSituation boardSituation) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(pawnMoveStrategy.getMovablePositions(source, boardSituation));
        positions.addAll(pawnAttackStrategy.getMovablePositions(source, boardSituation));
        return positions;
    }
}
