package chess.dto;

public class TurnDTO {

    private final String turn;

    public TurnDTO(String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }

    public boolean isInitTurn(String turnName) {
        return turn.equals(turnName);
    }
}
