package chess.piece;

import chess.chessgame.MovingPosition;
import chess.chessgame.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final List<Position> COORDINATES_OF_MOVABLE = List.of(
            new Position(-1, -2),
            new Position(-2, -1),
            new Position(-2, 1),
            new Position(-1, 2),
            new Position(1, 2),
            new Position(2, 1),
            new Position(2, -1),
            new Position(1, -2)
    );

    public Knight(Color color) {
        super(Type.KNIGHT, color);
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