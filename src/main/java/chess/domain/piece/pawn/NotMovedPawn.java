package chess.domain.piece.pawn;

import chess.domain.Team;
import chess.domain.piece.MovingStrategy;
import chess.domain.piece.Piece;
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
