package chess.dto;

public class CreateRequestDto {

    private final String gameName;

    public CreateRequestDto(final String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
}
