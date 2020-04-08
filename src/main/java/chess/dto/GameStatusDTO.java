package chess.dto;

public class GameStatusDTO {
    String nowPlayingTeam;
    String isGameEnd;

    public String getNowPlayingTeam() {
        return nowPlayingTeam;
    }

    public String getIsGameEnd() {
        return isGameEnd;
    }

    public void setNowPlayingTeam(String nowPlayingTeam) {
        this.nowPlayingTeam = nowPlayingTeam;
    }

    public void setIsGameEnd(String isGameEnd) {
        this.isGameEnd = isGameEnd;
    }
}
