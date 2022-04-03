package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class EmptyPiece extends Piece {

    private static final EmptyPiece emptyPiece = new EmptyPiece();

    private EmptyPiece() {
        super(Color.EMPTY, PieceType.EMPTY);
    }

    public static EmptyPiece getInstance() {
        return emptyPiece;
    }

    @Override
    public boolean canMove(Position fromPosition, Position toPosition) {
        return false;
    }

    @Override
    protected List<Direction> getMovableDirections() {
        throw new IllegalStateException("빈 피스는 갈 수 있는 방향이 없습니다.");
    }
}
