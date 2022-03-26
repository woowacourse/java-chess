package chess.piece;

import chess.chessgame.MovingPosition;
import chess.chessgame.Position;

import java.util.ArrayList;
import java.util.List;

public class Blank extends Piece {

    public Blank() {
        super(Type.BLANK, Color.NONE);
    }

    @Override
    public boolean isMovable(MovingPosition movingPosition) {
        return false;
    }

    @Override
    public List<Position> computeMiddlePosition(MovingPosition movingPosition) {
        return new ArrayList<>();
    }
}
