package chess.controller.dto;

public class MoveResultDto {
    private final String result;

    public MoveResultDto(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
