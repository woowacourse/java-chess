package chess.domain.piece.implementation.Strategy;

import chess.domain.board.BoardSituation;
import chess.domain.direction.MovingDirection;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

class PawnAttackStrategy {

    private List<MovingDirection> attackDirections;
    private Team team;

    PawnAttackStrategy(List<MovingDirection> attackDirections, Team team) {
        this.attackDirections = attackDirections;
        this.team = team;
    }

    List<Position> getMovablePositions(Position source, BoardSituation boardSituation) {
        return attackDirections.stream()
                .filter(source::canMoveBy)
                .map(source::moveByDirection)
                .filter(position -> boardSituation.canAttack(position, team))
                .collect(Collectors.toList());
    }
}
