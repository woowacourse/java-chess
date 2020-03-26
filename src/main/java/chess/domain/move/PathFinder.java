package chess.domain.move;

import chess.domain.move.direction.DirectionStrategy;
import chess.domain.move.direction.DownStrategy;
import chess.domain.move.direction.UpStrategy;
import chess.domain.position.Position;

public class PathFinder {
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

        }
        if (fileGap < 0 && rankGap == 0) {

        }
        if (fileGap > 0 && rankGap > 0) {

        }
        if (fileGap > 0 && rankGap < 0) {

        }
        if (fileGap < 0 && rankGap > 0) {

        }
        if (fileGap < 0 && rankGap < 0) {

        }
        return null;
    }
}
