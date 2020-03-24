package domain;

import domain.chesspiece.Chesspiece;

import java.util.List;
import java.util.Objects;

public class Board {

    private final List<List<Chesspiece>> chess;

    public Board(List<List<Chesspiece>> chess) {
        this.chess = chess;
    }

    public List<List<Chesspiece>> getChess() {
        return chess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board board = (Board) o;
        return Objects.equals(chess, board.chess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chess);
    }
}
