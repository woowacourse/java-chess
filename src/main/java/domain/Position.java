package domain;

import domain.piece.ChessFile;
import domain.piece.ChessRank;

import java.util.Objects;

public class Position {
    // TODO: 8x8 사이즈 포지션 캐싱하기

    private final ChessRank rank;
    private final ChessFile file;

    public Position(ChessRank rank, ChessFile file) {
        this.rank = rank;
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
