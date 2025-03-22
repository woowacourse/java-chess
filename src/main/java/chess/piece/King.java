package chess.piece;

import chess.Position;
import java.util.ArrayList;
import java.util.List;

public class King {


    public List<Position> calculateCanMovePosition(Position position) {
        List<Position> tmpPosition = new ArrayList<>();
        if (position.canMoveUp()) {
            tmpPosition.add(position.moveUp());
        }
        if (position.canMoveDown()) {
            tmpPosition.add(position.moveDown());
        }

        if (position.canMoveLeft()) {
            tmpPosition.add(position.moveLeft());
        }

        if (position.canMoveRight()) {
            tmpPosition.add(position.moveRight());
        }

        if (position.canMoveLeftUp()){
            tmpPosition.add(position.moveLeftUp());
        }

        if (position.canMoveLeftDown()){
            tmpPosition.add(position.moveLeftDown());
        }

        if (position.canMoveRightUp()) {
            tmpPosition.add(position.moveRightUp());
        }

        if (position.canMoveRightDown()) {
            tmpPosition.add(position.moveRightDown());
        }
        return tmpPosition;
    }
}
