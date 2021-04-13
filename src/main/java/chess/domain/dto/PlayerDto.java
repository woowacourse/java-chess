package chess.domain.dto;

public class PlayerDto {

    private int playerIndex;

    private String playerName;

    public PlayerDto(int playerIndex, String playerName) {
        this.playerIndex = playerIndex;
        this.playerName = playerName;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
