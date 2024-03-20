package domain;

import domain.piece.ChessFile;
import domain.piece.ChessRank;

import java.util.Objects;

public class Position {
    // TODO: 8x8 사이즈 포지션 캐싱하기

    private final ChessFile file;
    private final ChessRank rank;

    public Position(ChessFile file, ChessRank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(String position) {
        this(
                ChessFile.findByValue(String.valueOf(position.charAt(0))),
                ChessRank.findByValue(String.valueOf(position.charAt(1)))
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public int indexOfFile() {
        return file.index();
    }

    public int indexOfRank() {
        return rank.index();
    }
}
