package chess.domain.entity;

import java.util.Objects;

public final class PieceEntity {

    private final String type;
    private final int file;
    private final int rank;
    private final int gameId;

    public PieceEntity(String type, int file, int rank, int gameId) {
        this.type = type;
        this.file = file;
        this.rank = rank;
        this.gameId = gameId;
    }

    public String getType() {
        return type;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceEntity piece = (PieceEntity) o;
        return file == piece.file && rank == piece.rank && gameId == piece.gameId && Objects.equals(type, piece.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, file, rank, gameId);
    }

    @Override
    public String toString() {
        return "PieceEntity{" +
                "type='" + type + '\'' +
                ", file=" + file +
                ", rank=" + rank +
                ", gameId=" + gameId +
                '}';
    }
}
