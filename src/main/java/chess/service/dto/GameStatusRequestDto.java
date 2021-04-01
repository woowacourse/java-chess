package chess.service.dto;

public class GameStatusRequestDto {
    private boolean isGameOver;

    public GameStatusRequestDto(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
