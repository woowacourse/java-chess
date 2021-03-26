package chess.web.dto;

public class CheckFinishedResponseDto implements ResponseDto {
    private final boolean isFinished;

    public CheckFinishedResponseDto(boolean isFinished) {
        this.isFinished = isFinished;
    }
}
