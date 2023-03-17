package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Empty extends Piece {
    
    private Empty() {
        super(Color.NONE, PieceType.EMPTY, List.of());
    }
    
    public static Empty create() {
        return new Empty();
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
    }
}
