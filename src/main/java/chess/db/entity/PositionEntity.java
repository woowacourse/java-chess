package chess.db.entity;

import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.util.Objects;

public class PositionEntity {
    private final Long id;
    private final File file;
    private final Rank rank;

    public PositionEntity(Long id, String fileValue, String rankValue) {
        this.id = id;
        file = File.of(fileValue);
        rank = Rank.of(rankValue);
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
