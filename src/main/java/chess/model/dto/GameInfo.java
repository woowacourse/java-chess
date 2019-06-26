package chess.model.dto;

public class GameInfo {
    private String turn;
    private boolean isFinished;

    public String getTurn() {
        return turn;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }
}
