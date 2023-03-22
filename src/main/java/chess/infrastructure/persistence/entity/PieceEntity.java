package chess.infrastructure.persistence.entity;

public class PieceEntity {

    private final int rank;
    private final char file;
    private final String color;
    private final String movementType;
    private final Long chessGameId;

    public PieceEntity(final int rank, final char file, final String color, final String movementType, final Long chessGameId) {
        this.rank = rank;
        this.file = file;
        this.color = color;
        this.movementType = movementType;
        this.chessGameId = chessGameId;
    }

    public int rank() {
        return rank;
    }

    public char file() {
        return file;
    }

    public String color() {
        return color;
    }

    public String movementType() {
        return movementType;
    }

    public Long chessGameId() {
        return chessGameId;
    }
}
