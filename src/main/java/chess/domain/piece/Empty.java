package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Empty extends Piece {
    
    public static final String EMPTY_PIECE_CANNOT_MOVE_ERROR_MESSAGE = "빈 피스는 움직일 수 없습니다.";
    
    private Empty() {
        super(Color.NONE, PieceType.EMPTY, List.of());
    }
    
    public static Empty create() {
        return new Empty();
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        throw new IllegalStateException(EMPTY_PIECE_CANNOT_MOVE_ERROR_MESSAGE);
    }
}
