package chess.domain.dto.responseDto;

public class ResponseDto {
    private final boolean isSuccess;
    private final String jsonData;

    public ResponseDto(boolean isSuccess, String jsonData) {
        this.isSuccess = isSuccess;
        this.jsonData = jsonData;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getJsonData() {
        return jsonData;
    }
}
