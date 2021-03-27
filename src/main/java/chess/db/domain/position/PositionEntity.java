package chess.db.domain.position;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.position.type.Rank.SEVEN;
import static chess.domain.position.type.Rank.TWO;

import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;
import chess.domain.position.Position;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.util.Objects;

public class PositionEntity {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final Long id;
    private final File file;
    private final Rank rank;

    public PositionEntity(Long id, String fileValue, String rankValue) {
        this.id = id;
        file = File.of(fileValue);
        rank = Rank.of(rankValue);
    }

    public static PositionEntity of(File file, Rank rank) {
        return PositionEntitiesCache.find(file, rank);
    }

    public static PositionEntity of(String position) {
        String file = String.valueOf(position.charAt(FILE_INDEX));
        String rank = String.valueOf(position.charAt(RANK_INDEX));

        return PositionEntity.of(File.of(file), Rank.of(rank));
    }

    public Direction calculateDirection(Position destination) {
        File destinationFile = destination.file();
        Rank destinationRank = destination.rank();

        int fileDiff = destinationFile.order() - file.order();
        int rankDiff = destinationRank.value() - rank.value();

        return Direction.of(fileDiff, rankDiff);
    }

    public PositionEntity move(Direction direction) {
        return PositionEntity.of(file.move(direction), rank.move(direction));
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

    public Long getId() {
        return id;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PositionEntity)) {
            return false;
        }
        PositionEntity that = (PositionEntity) o;
        return id.equals(that.id) && file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, file, rank);
    }
}
