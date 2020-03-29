package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.strategy.MovedPawnStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;

import java.util.Map;

public class MovedPawn extends Pawn {
    private static final MovingStrategy strategy = new MovedPawnStrategy();

    private MovedPawn(Team team, Position position) {
        super(team, position);
    }

    public static MovedPawn of(Team team, Position position) {
        return new MovedPawn(team, position);
    }

    @Override
    public Piece move(Position from, Position to, Map<Position, Team> dto) {

        return this;
    }
}
