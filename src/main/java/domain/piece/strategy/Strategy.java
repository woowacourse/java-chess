package domain.piece.strategy;

import domain.direction.Direction;

public interface Strategy {

    Direction findDirection(int rowDifference, int columnDifference);
}
