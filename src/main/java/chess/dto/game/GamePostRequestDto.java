package chess.dto.game;

public class GamePostRequestDto {

    private final String whiteName;
    private final String blackName;

    public GamePostRequestDto(final String whiteName, final String blackName) {
        this.whiteName = whiteName;
        this.blackName = blackName;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public String getBlackName() {
        return blackName;
    }
}
