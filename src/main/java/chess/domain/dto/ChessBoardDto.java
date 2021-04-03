package chess.domain.dto;

public class ChessBoardDto {

    private String isOk;
    private PiecesDto piecesDto;
    private String message;
    private String turn;

    public ChessBoardDto(String isOk, PiecesDto piecesDto, String message, String turn) {
        this.isOk = isOk;
        this.piecesDto = piecesDto;
        this.message = message;
        this.turn = turn;
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
