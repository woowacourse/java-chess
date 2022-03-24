package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class EmptyPiece extends Piece{

    private static final EmptyPiece emptyPiece = new EmptyPiece();

    private EmptyPiece() {
        super(Color.EMPTY, PieceName.EMPTY);
    }

    public static EmptyPiece getInstance() {
        return emptyPiece;
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return null;
    }
}
