package chess.dto;

import java.util.List;

public class HistoryDto {
    private int round;
    private List<String> rows;
    private int turn;
    private boolean isKingDead;
    private boolean canMove;

    public HistoryDto() {
        this.isKingDead = false;
    }

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

    public boolean isKingDead() {
        return isKingDead;
    }

    public void setKingDead(boolean kingDead) {
        isKingDead = kingDead;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    @Override
    public String toString() {
        return "HistoryDto{" +
                "round=" + round +
                ", rows=" + rows +
                ", turn=" + turn +
                ", isKingDead=" + isKingDead +
                ", canMove=" + canMove +
                '}';
    }
}
