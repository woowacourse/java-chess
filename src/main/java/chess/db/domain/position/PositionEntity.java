package chess.db.domain.position;

import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.position.type.Rank.SEVEN;
import static chess.beforedb.domain.position.type.Rank.TWO;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;
import chess.beforedb.domain.position.type.File;
import chess.beforedb.domain.position.type.Rank;
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

    public PositionEntity(File file, Rank rank) {
        id = null;
        this.file = file;
        this.rank = rank;
    }

    public static PositionEntity of(File file, Rank rank) {
        return PositionEntitiesCache.find(file, rank);
    }

    public static PositionEntity of(String position) {
        String file = String.valueOf(position.charAt(FILE_INDEX));
        String rank = String.valueOf(position.charAt(RANK_INDEX));

        return PositionEntity.of(File.of(file), Rank.of(rank));
    }

    public static PositionEntity of(Long positionId) {
        return PositionEntitiesCache.findById(positionId);
    }

    public Direction calculateDirection(PositionEntity destination) {
        File destinationFile = destination.getFile();
        Rank destinationRank = destination.getRank();

        int fileDiff = destinationFile.getOrder() - file.getOrder();
        int rankDiff = destinationRank.getValue() - rank.getValue();

        return Direction.of(fileDiff, rankDiff);
    }

    public PositionEntity moveTo(Direction direction) {
        return PositionEntity.of(file.getMovedFile(direction), rank.getMovedRank(direction));
    }

    public boolean isRankForwardedBy(PositionEntity destination, int rankDiff) {
        return rank.isDiff(destination.getRank(), rankDiff)
            && file.isSameAs(destination.getFile());
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
        return getFile() == that.getFile() && getRank() == that.getRank();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFile(), getRank());
    }
}
