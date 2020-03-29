package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.piece.strategy.NotMovedPawnStrategy;
import chess.domain.position.Position;

import java.util.Map;

public class NotMovedPawn extends Pawn {
    private static final MovingStrategy strategy = new NotMovedPawnStrategy();

    private NotMovedPawn(Team team, Position position) {
        super(team, position);
    }

    public static NotMovedPawn of(Team team, Position position) {
        return new NotMovedPawn(team, position);
    }

    @Override
    public Piece move(Position from, Position to, Map<Position, Team> dto) {
        return MovedPawn.of(team, to);
    }
}
