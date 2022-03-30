package chess.domain.position.direction;

import chess.domain.position.Position;

public class KnightDirection {
    public static boolean isOnSevenShape(Position from, Position to) {
        boolean condition1 = Math.abs(from.getXAxis().getValue() - to.getXAxis().getValue()) == 2
                && Math.abs(from.getYAxis().getValue() - to.getYAxis().getValue()) == 1;

        boolean condition2 = Math.abs(from.getXAxis().getValue() - to.getXAxis().getValue()) == 1
                && Math.abs(from.getYAxis().getValue() - to.getYAxis().getValue()) == 2;

        return condition1 || condition2;
    }
}
