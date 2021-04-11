package chess.dto;

public class TurnChangeRequestDto {
    private final String currentTurn;
    private final String nextTurn;

    public TurnChangeRequestDto(final String currentTurn, final String nextTurn) {
        this.currentTurn = currentTurn;
        this.nextTurn = nextTurn;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public String getNextTurn() {
        return nextTurn;
    }
}
