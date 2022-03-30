package chess.domain.piece;

import chess.domain.board.Chessboard;
import chess.domain.position.Position;

import java.util.Map;

public class Blank extends Piece {

    public Blank() {
        super(Type.BLANK, Color.NONE);
    }

    @Override
    public boolean isMovableDot(Position source, Position target) {
        return false;
    }

    @Override
    public boolean isMovableLine(Position source, Position target, Map<Position, Piece> board) {
        return false;
    }
}
