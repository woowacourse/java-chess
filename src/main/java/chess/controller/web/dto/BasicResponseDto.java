package chess.controller.web.dto;

import java.util.Objects;

public class BasicResponseDto {
    public static final WebResponseDto EMPTY_DATA = new WebResponseDto() {
    };
    private static final String EMPTY_ERROR_MSG = "";

    private final boolean isError;
    private final String errorMsg;
    private final WebResponseDto data;

    public BasicResponseDto(boolean isError, String errorMsg, WebResponseDto data) {
        this.isError = isError;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static BasicResponseDto createSuccessResponseDto(WebResponseDto data) {
        return new BasicResponseDto(false, EMPTY_ERROR_MSG, data);
    }

    public static BasicResponseDto createFailResponseDto(String errorMsg) {
        return new BasicResponseDto(false, errorMsg, EMPTY_DATA);
    }

    public boolean isError() {
        return isError;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public WebResponseDto getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicResponseDto)) return false;
        BasicResponseDto that = (BasicResponseDto) o;
        return isError() == that.isError() && Objects.equals(getErrorMsg(), that.getErrorMsg()) && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isError(), getErrorMsg(), getData());
    }
}
