package chess.controller.web.dto;

public class MoveResponseDto implements WebResponseDto {
    private final boolean isEnd;

    public MoveResponseDto(boolean inEnd) {
        this.isEnd = inEnd;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
