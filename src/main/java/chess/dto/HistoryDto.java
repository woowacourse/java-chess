package chess.dto;

import java.util.List;

public class HistoryDto {
    private int round;
    private List<String> rows;
    private int turn;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return "HistoryDto{" +
                "round=" + round +
                ", rows=" + rows +
                ", turn=" + turn +
                '}';
    }
}
