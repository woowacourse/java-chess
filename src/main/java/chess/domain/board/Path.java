package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class Path {

    private Map<Position, Piece> path;
    private Position start;
    private Position end;

    public Path(final Map<Position, Piece> path, final Position start, final Position end) {
        this.path = path;
        this.start = start;
        this.end = end;
    }
}
