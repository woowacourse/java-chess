package chess.domain.piece;

import java.util.Collections;
import java.util.List;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;

public class Queen extends AbstractPiece {

    public Queen(Team team) {
        super(PieceType.QUEEN, team);
    }

    @Override
    public List<Coordinate> findMovablePath(Coordinate start, Coordinate destination) {
        Direction direction = Direction.of(destination.getFile() - start.getFile(), destination.getRank() - start.getRank());
        List<Coordinate> path = direction.createPath(start);
        if (path.contains(destination)) {
            return path;
        }

        return Collections.emptyList();
    }
}
