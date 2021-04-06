package chess.dto;

public class BoardResponseDto {

    private final PiecesDto piecesDto;
    private final boolean isRunning;
    private final String message;
    private final boolean isSuccessful;

    public BoardResponseDto(final PiecesDto piecesDto, final boolean isRunning,
        final String message, final boolean isSuccessful) {
        this.piecesDto = piecesDto;
        this.isRunning = isRunning;
        this.message = message;
        this.isSuccessful = isSuccessful;
    }

    public static BoardResponseDto createWithSuccess(PiecesDto piecesDto, boolean isRunning) {
        return new BoardResponseDto(piecesDto, isRunning, "이동 성공", true);
    }

    public static BoardResponseDto createWithFailure(boolean isRunning, String message) {
        return new BoardResponseDto(PiecesDto.createEmpty(), isRunning, message, false);
    }

    public PiecesDto getPiecesDto() {
        return piecesDto;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
