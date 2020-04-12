package chess.dto;

public class CommandDto {
    private String command;

    public CommandDto(String command) {
        this.command = command;
    }

    public String get() {
        return command;
    }
}