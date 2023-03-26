package chess.entity;

import java.util.Objects;

public final class PieceEntity {
    private Long id;
    private final Integer rank;
    private final Integer file;
    private final String pieceType;
    private final String campType;

    public PieceEntity(final Long id, final Integer rank, final Integer file,
                       final String pieceType, final String campType) {
        this.id = id;
        this.rank = rank;
        this.file = file;
        this.pieceType = pieceType;
        this.campType = campType;
    }

    public PieceEntity(final Integer rank, final Integer file, final String pieceType, final String campType) {
        this.rank = rank;
        this.file = file;
        this.pieceType = pieceType;
        this.campType = campType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PieceEntity that = (PieceEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(rank, that.rank) && Objects.equals(file, that.file) && Objects.equals(pieceType, that.pieceType) && Objects.equals(campType, that.campType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rank, file, pieceType, campType);
    }

    public Integer getRank() {
        return rank;
    }

    public Integer getFile() {
        return file;
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getCampType() {
        return campType;
    }
}
