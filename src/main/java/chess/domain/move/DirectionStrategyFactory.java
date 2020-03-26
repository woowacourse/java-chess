package chess.domain.move;

import chess.domain.move.direction.*;
import chess.domain.position.Position;

public class DirectionStrategyFactory {
    public static DirectionStrategy find(Position source, Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);

        if (fileGap == 0 && rankGap > 0) {
            return new UpStrategy();
        }
        if (fileGap == 0 && rankGap < 0) {
            return new DownStrategy();
        }
        if (fileGap > 0 && rankGap == 0) {
            return new RightStrategy();
        }
        if (fileGap < 0 && rankGap == 0) {
            return new LeftStrategy();
        }
        if (fileGap > 0 && rankGap > 0) {
            return new RightUpStrategy();
        }
        if (fileGap > 0 && rankGap < 0) {
            return new RightDownStrategy();
        }
        if (fileGap < 0 && rankGap > 0) {
            return new LeftUpStrategy();
        }
        if (fileGap < 0 && rankGap < 0) {
            return new LeftDownStrategy();
        }
        throw new AssertionError();
    }
}
