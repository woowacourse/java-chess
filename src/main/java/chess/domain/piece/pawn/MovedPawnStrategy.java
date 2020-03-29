package chess.domain.piece.pawn;

import chess.domain.Team;
import chess.domain.piece.MovingStrategy;
import chess.domain.position.Position;

import java.util.Map;

public class MovedPawnStrategy extends MovingStrategy {
    @Override
    protected void checkObstacle(Position source, Position target, Map<Position, Team> boardDto) {

    }

    @Override
    protected void checkDirection(Position source, Position target) {

    }
}
