package chess.dto;

public class RoundInfoDto {
    private int round;
    private String whitePlayer;
    private String blackPlayer;
    private boolean isEnd;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(String whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(String blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    @Override
    public String toString() {
        return "RoundInfoDto{" +
                "round=" + round +
                ", blackPlayer='" + blackPlayer + '\'' +
                ", whitePlayer='" + whitePlayer + '\'' +
                ", isEnd=" + isEnd +
                '}';
    }
}
