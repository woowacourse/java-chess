package chess.domain.piece;

import chess.domain.board.Position;
import java.util.Map;

public class NoPiece extends Piece {

    public NoPiece(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        return false;
    }

    @Override
    public boolean exists() {
        return false;
    }
}
