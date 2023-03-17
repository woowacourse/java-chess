package chess.domain;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.List;

public abstract class AbstractTestFixture {

    public static Move createMove(Direction... directions) {
        return new Move(List.of(directions));
    }
}
