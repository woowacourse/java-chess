package chess.piece;

import chess.Position;
import java.util.ArrayList;
import java.util.List;

public class King {

    public List<Position> calculateCanMovePosition(Position departure, Position arrival) {
        List<Position> tmpPosition = new ArrayList<>();
        if (departure.canMoveUp()) {
            Position movedPosition = departure.moveUp();
            if (arrival.equals(movedPosition)) {
                return List.of(movedPosition);
            }
        }
        if (departure.canMoveDown()) {
            Position movedPosition = departure.moveDown();
            if (arrival.equals(movedPosition)) {
                return List.of(movedPosition);
            }
        }

        if (departure.canMoveLeft()) {
            Position movedPosition = departure.moveLeft();
            if (arrival.equals(movedPosition)) {
                return List.of(movedPosition);
            }
        }

        if (departure.canMoveRight()) {
            Position movedPosition = departure.moveRight();
            if (arrival.equals(movedPosition)) {
                return List.of(movedPosition);
            }
        }

        if (departure.canMoveRightUp()) {
            Position movedPosition = departure.moveRightUp();
            if (arrival.equals(movedPosition)) {
                return List.of(movedPosition);
            }
        }

        if (departure.canMoveRightDown()) {
            Position movedPosition = departure.moveRightDown();
            if (arrival.equals(movedPosition)) {
                return List.of(movedPosition);
            }
        }

        if (departure.canMoveLeftUp()) {
            Position movedPosition = departure.moveLeftUp();
            if (arrival.equals(movedPosition)) {
                return List.of(movedPosition);
            }
        }

        if (departure.canMoveLeftDown()) {
            Position movedPosition = departure.moveLeftDown();
            if (arrival.equals(movedPosition)) {
                return List.of(movedPosition);
            }
        }
        throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
    }
}
