package chess.domain.position;

import chess.domain.player.Team;

public class MoveChecker {

    private static final int KNIGHT_MOVE_DISTANCE = 3;

    public static boolean isLinear(final Position current, final Position destination) {
        final File currentFile = current.getFile();
        final Rank currentRank = current.getRank();
        final int fileDistance = currentFile.calculateFileInAbsolute(destination.getFile());
        final int rankDistance = currentRank.calculateRankInAbsolute(destination.getRank());

        return fileDistance == 0 && rankDistance > 0 || fileDistance > 0 && rankDistance == 0;
    }

    public static boolean isDiagonal(final Position current, final Position destination) {
        final File currentFile = current.getFile();
        final Rank currentRank = current.getRank();
        final int fileDistance = currentFile.calculateFileInAbsolute(destination.getFile());
        final int rankDistance = currentRank.calculateRankInAbsolute(destination.getRank());

        return fileDistance + rankDistance != 0 && fileDistance == rankDistance;
    }

    public static boolean isForKnight(final Position current, final Position destination) {
        final File currentFile = current.getFile();
        final Rank currentRank = current.getRank();
        final int fileDistance = currentFile.calculateFileInAbsolute(destination.getFile());
        final int rankDistance = currentRank.calculateRankInAbsolute(destination.getRank());

        if (fileDistance + rankDistance != KNIGHT_MOVE_DISTANCE) {
            return false;
        }
        return fileDistance != 0 && rankDistance != 0;
    }

    public static boolean isForward(final Position current, final Position destination, final Team team) {
        final File currentFile = current.getFile();
        final Rank currentRank = current.getRank();
        final File destinationFile = destination.getFile();
        final Rank destinationRank = destination.getRank();

        if (currentFile != destinationFile) {
            return false;
        }
        if (team == Team.BLACK) {
            return currentRank.calculateRank(destinationRank) > 0;
        }
        return destinationRank.calculateRank(currentRank) > 0;
    }

    public static boolean isDiagonalForward(final Position current, final Position destination, final Team team) {
        final File currentFile = current.getFile();
        final Rank currentRank = current.getRank();
        final Rank destinationRank = destination.getRank();
        final int fileDistance = currentFile.calculateFileInAbsolute(destination.getFile());
        final int rankDistance = destinationRank.calculateRank(currentRank);

        if (team == Team.BLACK) {
            return fileDistance > 0 && fileDistance == Math.abs(rankDistance);
        }
        return fileDistance > 0 && fileDistance == rankDistance;
    }
}
