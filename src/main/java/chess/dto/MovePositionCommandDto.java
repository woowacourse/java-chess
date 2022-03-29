package chess.dto;

public class MovePositionCommandDto {

    private static final String COMMAND_INPUT_DELIMITER = " ";

    private final String source;
    private final String target;

    public MovePositionCommandDto(String command) {
        String[] commandInput = command.split(COMMAND_INPUT_DELIMITER);
        this.source = commandInput[1];
        this.target = commandInput[2];
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

}
