package chess.domain;

import java.util.List;

public abstract class AbstractTestFixture {

    public static Movement createMovement(Direction... directions) {
        return new Movement(List.of(directions));
    }

    public static Position createPosition(String positon) {
        String[] split = positon.split(",");
        File file = File.valueOf(split[0]);
        Rank rank = Rank.valueOf(split[1]);
        return new Position(file, rank);
    }
}
