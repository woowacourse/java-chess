package chess.dto;

public class RequestDto {
    private final String firstInfo;
    private final String secondInfo;

    public RequestDto(String firstInfo, String secondInfo) {
        this.firstInfo = firstInfo;
        this.secondInfo = secondInfo;
    }

    public String getFirstInfo() {
        return firstInfo;
    }

    public String getSecondInfo() {
        return secondInfo;
    }
}
