package chess.web.dto.responsedto;

public class CheckFinishedResponseDto implements ResponseDto {
    private final boolean isFinished;

    public CheckFinishedResponseDto(boolean isFinished) {
        this.isFinished = isFinished;
    }
}
