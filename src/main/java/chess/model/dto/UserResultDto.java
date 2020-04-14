package chess.model.dto;

public class UserResultDto {

    private final String winOrDraw;

    public UserResultDto(String winOrDraw) {
        this.winOrDraw = winOrDraw;
    }

    public String getWinOrDraw() {
        return winOrDraw;
    }

}
