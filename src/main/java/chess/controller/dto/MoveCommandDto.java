package chess.controller.dto;

import java.util.Arrays;
import java.util.List;

public class MoveCommandDto {
    private static final String SPLITTER = " ";

    private List<String> runCommand;

    public MoveCommandDto(String runCommand) {
        this.runCommand = Arrays.asList(runCommand.split(SPLITTER));
    }

    public List<String> get() {
        return runCommand;
    }
}
