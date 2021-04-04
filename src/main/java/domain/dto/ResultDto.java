package domain.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultDto {
    private boolean success = true;
    private String response = "";
    private String errorMessage;

    public ResultDto(PiecesDto piecesDto, String errorMessage) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        response += mapper.writeValueAsString(piecesDto);
        this.errorMessage = errorMessage;
        checkSuccess(errorMessage);
    }

    private void checkSuccess(String errorMessage) {
        if (errorMessage != "") {
            success = false;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public String getResponse() {
        return response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
