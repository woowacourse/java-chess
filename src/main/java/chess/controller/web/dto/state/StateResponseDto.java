package chess.controller.web.dto.state;

public class StateResponseDto {

    private final String turnOwner;
    private final int turnNumber;
    private final boolean isPlaying;

    public StateResponseDto(final String turnOwner, final int turnNumber, final boolean isPlaying) {
        this.turnOwner = turnOwner;
        this.turnNumber = turnNumber;
        this.isPlaying = isPlaying;
    }

    public String getTurnOwner() {
        return turnOwner;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
