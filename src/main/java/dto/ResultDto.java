package dto;

public class ResultDto {
    private PiecesDto piecesDto;
    private boolean success = true;
    private String errorMessage;

    public ResultDto(PiecesDto piecesDto, String errorMessage) {
        this.piecesDto = piecesDto;
        this.errorMessage = errorMessage;
        checkSuccess(errorMessage);
    }

    private void checkSuccess(String errorMessage) {
        if (errorMessage != "") {
            success = false;
        }
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
}
