package dto.request;

public class ChessGameRequestDto {

    private final boolean isPlaying;

    public ChessGameRequestDto(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

}
