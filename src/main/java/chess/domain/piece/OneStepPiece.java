package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public abstract class OneStepPiece extends Piece {

    public OneStepPiece(Team team, Position position) {
        super(team, position);
    }

    public List<Position> findPath(Position destination) {
        validateIsPossibleDst(destination);
        return List.of();
    }

    protected abstract void validateIsPossibleDst(Position destination);
}
