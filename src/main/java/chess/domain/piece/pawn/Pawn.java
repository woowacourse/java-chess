package chess.domain.piece.pawn;

import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public abstract class Pawn extends Initialized {
    Pawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team, canNotMoveStrategies);
    }
}
