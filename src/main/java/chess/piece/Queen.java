package chess.piece;

import chess.chessgame.MovingPosition;
import chess.chessgame.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    public boolean isMovable(MovingPosition movingPosition) {
        return movingPosition.isCross() || movingPosition.isLinear();
    }

    @Override
    public List<Position> computeMiddlePosition(MovingPosition movingPosition) {
        List<Position> list = new ArrayList<>();
        list.addAll(movingPosition.computeLinearMiddle());
        list.addAll(movingPosition.computeCrossMiddle());

        return list;
    }

}
