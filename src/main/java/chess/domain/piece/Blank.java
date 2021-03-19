package chess.domain.piece;

import chess.domain.board.Direction;
import java.util.List;

public class Blank extends Piece {

    public Blank(Color color) {
        super(color, false);
        this.type = Type.BLANK;
    }

    @Override
    public List<Direction> direction() {
        throw new UnsupportedOperationException();
    }
}
