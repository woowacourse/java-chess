package chess.entity;

public final class PieceEntity {
    private Long id;
    private final Long chessGameId;
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

    public static PieceEntity create(final Long id, final Long chessGameId, final Integer rank,
                                     final Integer file, final String pieceType, final String campType) {
        return new PieceEntity(id, chessGameId, rank, file, pieceType, campType);
    }

    public static PieceEntity createWithChessGameId(final Long chessGameId, final Integer rank, final Integer file,
                                                    final String pieceType, final String campType) {
        return new PieceEntity(null, chessGameId, rank, file, pieceType, campType);
    }

    public static PieceEntity createWithLocation(final Long chessGameId, final Integer rank, final Integer file) {
        return new PieceEntity(chessGameId, rank, file);
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

    public Long getId() {
        return id;
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
