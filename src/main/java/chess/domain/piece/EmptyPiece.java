package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class EmptyPiece extends Piece {

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece();

    private EmptyPiece() {
        super(Color.EMPTY, PieceName.EMPTY);
    }

    public static EmptyPiece getInstance() {
        return EMPTY_PIECE;
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        throw new IllegalStateException("해당 자리에는 말이 존재하지 않습니다.");
    }

    @Override
    public double getPoint() {
        return 0;
    }
}
