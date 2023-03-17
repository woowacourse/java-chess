package chess.domain.piece;

import static chess.domain.MoveStrategy.KNIGHT;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return KNIGHT.isMovable(source, target);
    }

    @Override
    public List<Position> findPath(Position source, Position target) {
        return List.of(target);
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        throw new UnsupportedOperationException();
    }
}
