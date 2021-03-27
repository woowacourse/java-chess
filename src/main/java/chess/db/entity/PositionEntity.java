package chess.db.entity;

import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.util.Objects;

public class PositionEntity {
    private Long id;
    private String fileValue;
    private String rankValue;
    private PieceEntity pieceEntity;

    public PositionEntity(File file, Rank rank, PieceEntity pieceEntity) {
        this.fileValue = file.value();
        this.rankValue = String.valueOf(rank.value());
        this.pieceEntity = pieceEntity;
    }

    public PositionEntity(Long id, String fileValue, String rankValue, PieceEntity pieceEntity) {
        this.id = id;
        this.fileValue = fileValue;
        this.rankValue = rankValue;
        this.pieceEntity = pieceEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileValue() {
        return fileValue;
    }

    public void setFile(File file) {
        fileValue = file.value();
    }

    public String getRankValue() {
        return rankValue;
    }

    public void setRank(Rank rank) {
        rankValue = String.valueOf(rank.value());
    }

    public PieceEntity getPieceEntity() {
        return pieceEntity;
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
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
