package chess.piece;

import chess.chessgame.MovingPosition;
import chess.chessgame.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final List<Position> COORDINATES_OF_MOVABLE = List.of(
            new Position(0, 1),
            new Position(0, -1),
            new Position(1, 1),
            new Position(1, 0),
            new Position(1, -1),
            new Position(-1, 1),
            new Position(-1, 0),
            new Position(-1, -1)
    );

    public King(Color color) {
        super(Type.KING, color);
    }

    @Override
    public boolean isMovable(MovingPosition movingPosition) {
        return movingPosition.isAnyPossible(COORDINATES_OF_MOVABLE);
    }

    @Override
    public List<Position> computeMiddlePosition(MovingPosition movingPosition) {
        return new ArrayList<>();
    }

}
