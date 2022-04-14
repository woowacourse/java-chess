package chess.web.dto;

public class MoveReqDto {
    private final String command;

    public MoveReqDto(final String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
