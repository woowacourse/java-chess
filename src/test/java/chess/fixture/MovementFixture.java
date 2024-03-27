package chess.fixture;

import chess.domain.square.File;
import chess.domain.square.Movement;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.ArrayList;
import java.util.List;

public class MovementFixture {
    private MovementFixture() {
    }

    public static List<Movement> createMovements() {
        List<Movement> movements = new ArrayList<>();
        movements.add(new Movement(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.THREE)));
        movements.add(new Movement(Square.of(File.A, Rank.SEVEN), Square.of(File.A, Rank.FIVE)));
        movements.add(new Movement(Square.of(File.C, Rank.TWO), Square.of(File.C, Rank.THREE)));
        return movements;
    }
}
