package dto;

public class ResultDto {
    private PiecesDto piecesDto;
    private boolean success = true;
    private String errorMessage;

    public ResultDto(PiecesDto piecesDto, boolean succes, String errorMessage) {
        this.piecesDto = piecesDto;
        this.success = succes;
        this.errorMessage = errorMessage;
    }

    public PiecesDto getPiecesDto() {
        return this.piecesDto;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isEnd() {
        return piecesDto.isEnd();
    }
}
