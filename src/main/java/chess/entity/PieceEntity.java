package chess.entity;

import java.util.Objects;

public final class PieceEntity {
    private Long id;
    private Long chessGameId;
    private final Integer rank;
    private final Integer file;
    private String pieceType;
    private String campType;

    private PieceEntity(final Long id, final Long chessGameId, final Integer rank,
                        final Integer file, final String pieceType, final String campType) {
        this.id = id;
        this.chessGameId = chessGameId;
        this.rank = rank;
        this.file = file;
        this.pieceType = pieceType;
        this.campType = campType;
    }

    public PieceEntity(final Long chessGameId, final Integer rank, final Integer file) {
        this.chessGameId = chessGameId;
        this.rank = rank;
        this.file = file;
    }

    public static PieceEntity createWithId(final Long id, final Integer rank, final Integer file,
                                           final String pieceType, final String campType) {
        return new PieceEntity(id, null, rank, file, pieceType, campType);
    }

    public static PieceEntity createWithChessGameId(final Long chessGameId, final Integer rank, final Integer file,
                                                    final String pieceType, final String campType) {
        return new PieceEntity(null, chessGameId, rank, file, pieceType, campType);
    }

    public static PieceEntity createWithLocation(final Long chessGameId, final Integer rank, final Integer file) {
        return new PieceEntity(chessGameId, rank, file);
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

    @Override
    public String toString() {
        return "PieceEntity{" +
                "id=" + id +
                ", chessGameId=" + chessGameId +
                ", rank=" + rank +
                ", file=" + file +
                ", pieceType='" + pieceType + '\'' +
                ", campType='" + campType + '\'' +
                '}';
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

    public Long getChessGameId() {
        return chessGameId;
    }
}
