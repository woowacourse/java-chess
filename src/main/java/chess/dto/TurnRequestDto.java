package chess.dto;

public class TurnRequestDto {
    private final Long id;
    private final String currentTurn;

    public TurnRequestDto(final Long id, final String currentTurn) {
        this.id = id;
        this.currentTurn = currentTurn;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }
}
