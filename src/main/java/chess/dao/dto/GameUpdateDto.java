package chess.dao.dto;

public class GameUpdateDto {

    private final Long id;
    private final Boolean finished;
    private final String currentTurnColor;

    public GameUpdateDto(final Long id, final Boolean finished, final String currentTurnColor) {
        this.id = id;
        this.finished = finished;
        this.currentTurnColor = currentTurnColor;
    }

    public Long getId() {
        return id;
    }

    public Boolean getFinished() {
        return finished;
    }

    public String getCurrentTurnColor() {
        return currentTurnColor;
    }
}
