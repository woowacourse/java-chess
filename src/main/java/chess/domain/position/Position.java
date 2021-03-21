package chess.domain.position;

import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;
import static chess.domain.player.type.TeamColor.BLACK;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;
import chess.domain.position.cache.PositionsCache;
import java.util.Objects;

public class Position {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return PositionsCache.find(file, rank);
    }

    public static Position of(String positionInput) {
        String fileInput = String.valueOf(positionInput.charAt(FILE_INDEX));
        String rankInput = String.valueOf(positionInput.charAt(RANK_INDEX));

        return Position.of(File.of(fileInput), Rank.of(rankInput));
    }

    public Direction calculateDirection(Position toPosition) {
        File targetFile = toPosition.file();
        Rank targetRank = toPosition.rank();

        int fileDiff = targetFile.order() - file.order();
        int rankDiff = targetRank.value() - rank.value();

        return Direction.of(fileDiff, rankDiff);
    }

    public Position move(Direction direction) {
        return new Position(file.move(direction), rank.move(direction));
    }

    public boolean isRankForwardedBy(Position destination, int rankDiff) {
        return rank.isDiff(destination.rank(), rankDiff)
            && file.isSameAs(destination.file());
    }

    public boolean isFirstPawnPosition(TeamColor teamColor) {
        if (teamColor == BLACK) {
            return rank == SEVEN;
        }
        return rank == TWO;
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
