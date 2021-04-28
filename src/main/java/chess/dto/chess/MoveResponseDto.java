package chess.dto.chess;

public class MoveResponseDto {

    private final boolean isFinished;
    private final String winner;

    public MoveResponseDto(final boolean isFinished, final String winner) {
        this.isFinished = isFinished;
        this.winner = winner;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String getWinner() {
        return winner;
    }
}
