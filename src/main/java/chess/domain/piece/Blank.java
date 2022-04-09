package chess.domain.piece;

import java.util.List;

public class Blank extends Piece {
    public Blank() {
        super("", List.of(Direction.NONE), Team.NONE);
    }

    @Override
    public float getScore() {
        return 0;
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public String toString() {
        return "blank";
    }
}
