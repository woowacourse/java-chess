package fixture;

import domain.File;
import domain.Position;
import domain.Rank;

public class PositionFixture {

    public static Position d4() {
        return new Position(File.D, Rank.FOUR);
    }
}
