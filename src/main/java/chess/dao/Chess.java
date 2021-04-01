package chess.dao;

import java.util.Objects;

public class Chess {
    private String chessId;
    private String chess;
    private String turn;

    public Chess(String chessId, String chess, String turn) {
        this.chessId = chessId;
        this.chess = chess;
        this.turn = turn;
    }

    public String getChessId() {
        return chessId;
    }

    public String getChess() {
        return chess;
    }

    public String getTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chess chess1 = (Chess) o;
        return Objects.equals(chess, chess1.chess) && Objects.equals(turn, chess1.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chess, turn);
    }
}
