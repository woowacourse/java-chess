package chess.domain.piece;

import chess.domain.board.BoardState;
import chess.domain.direction.MovingDirection;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Pawn extends Piece {

    protected static final Map<Team, MovingDirection> MOVING_DIRECTION_BY_TEAM;
    protected static final Map<Team, List<MovingDirection>> ATTACK_DIRECTION_BY_TEAM;

    static {
        MOVING_DIRECTION_BY_TEAM = new HashMap<>();
        MOVING_DIRECTION_BY_TEAM.put(Team.WHITE, MovingDirection.NORTH);
        MOVING_DIRECTION_BY_TEAM.put(Team.BLACK, MovingDirection.SOUTH);

        ATTACK_DIRECTION_BY_TEAM = new HashMap<>();
        ATTACK_DIRECTION_BY_TEAM.put(Team.WHITE, Arrays.asList(
                MovingDirection.NORTH_EAST,
                MovingDirection.NORTH_WEST
        ));
        ATTACK_DIRECTION_BY_TEAM.put(Team.BLACK, Arrays.asList(
                MovingDirection.SOUTH_EAST,
                MovingDirection.SOUTH_WEST
        ));
    }

    protected Pawn(Position position, Team team) {
        super(PieceType.PAWN, position, team);
    }

    @Override
    public List<Position> getMovablePositions(BoardState boardState) {
        List<Position> positions = attckPositions(boardState);
        positions.addAll(movePositions(boardState));
        return positions;
    }

    protected abstract List<Position> movePositions(BoardState boardState);

    private List<Position> attckPositions(BoardState boardState) {
        List<Position> attackPositions = new ArrayList<>();
        List<MovingDirection> attackDirections = ATTACK_DIRECTION_BY_TEAM.get(team);
        for (MovingDirection attackDirection : attackDirections) {
            canAttackBy(attackDirection, boardState, attackPositions);
        }
        return attackPositions;
    }

    private void canAttackBy(MovingDirection attackDirection, BoardState boardState, List<Position> attackPositions) {
        Position startPosition = position;
        if (startPosition.canMove(attackDirection)) {
            startPosition = startPosition.moveByDirection(attackDirection);
            if (boardState.canAttack(startPosition, team)) {
                attackPositions.add(startPosition);
            }
        }
    }
}
