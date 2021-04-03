package chess.domain.dto;

public class ChessBoardDto {

    private String isOk;
    private PiecesDto piecesDto;
    private String message;

    public ChessBoardDto(String isOk, PiecesDto piecesDto, String message) {
        this.isOk = isOk;
        this.piecesDto = piecesDto;
        this.message = message;
    }

    public String getIsOk() {
        return isOk;
    }

    public PiecesDto getPiecesDto() {
        return piecesDto;
    }

    public String getMessage() {
        return message;
    }
}
