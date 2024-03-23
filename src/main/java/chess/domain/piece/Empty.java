package chess.domain.piece;

import java.util.Collections;
import java.util.List;
import chess.domain.board.Coordinate;

public class Empty extends AbstractPiece {

    public Empty() {
        super(PieceType.EMPTY, Team.EMPTY);
    }

    @Override
    public List<Coordinate> findMovablePath(Coordinate start, Coordinate destination) {
        return Collections.emptyList();
    }
}
