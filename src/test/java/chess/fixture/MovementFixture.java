package chess.fixture;

import chess.domain.square.Movement;
import chess.domain.square.Square;
import java.util.ArrayList;
import java.util.List;

public class MovementFixture {
    private MovementFixture() {
    }

    public static List<Movement> createMovements() {
        List<Movement> movements = new ArrayList<>();
        movements.add(new Movement(Square.from("a2"), Square.from("a3")));
        movements.add(new Movement(Square.from("a7"), Square.from("a5")));
        movements.add(new Movement(Square.from("c2"), Square.from("c3")));
        return movements;
    }
}
