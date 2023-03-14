package chess.domain;

import java.util.List;

public abstract class AbstractTestFixture {

    public static Movement createMovement(Direction... directions) {
        return new Movement(List.of(directions));
    }
}
