package chess.dto;

public class MovePositionCommandDto {

    private static final int SOURCE_POSITION = 1;
    private static final int TARGET_POSITION = 2;

    private static final String COMMAND_INPUT_DELIMITER = " ";

    private final String source;
    private final String target;

    public MovePositionCommandDto(String command) {
        String[] commandInput = command.split(COMMAND_INPUT_DELIMITER);
        this.source = commandInput[SOURCE_POSITION];
        this.target = commandInput[TARGET_POSITION];
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

}
