package chess.dto;

import chess.view.Command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandDto {

    private static final String MOVE_REGEX = " ";
    private static final String SOURCE_REGEX = "";
    private static final int COLUMN_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int END_SOURCE_INDEX = 2;
    private static final int START_SOURCE_INDEX = 1;
    private final Command command;
    private String columnOfStartSource;
    private String rankOfStartSource;
    private String columnOfEndSource;
    private String rankOfEndSource;

    public CommandDto(Command command, String input) {
        this.command = command;
        if (command == Command.MOVE) {
            setMoveCommand(input);
        }
    }

    private void setMoveCommand(String input) {
        List<String> moveCommand = Arrays.stream(input.split(MOVE_REGEX))
                .map(each -> each.strip())
                .collect(Collectors.toList());

        setStartPosition(moveCommand);
        setEndPosition(moveCommand);
    }

    private void setEndPosition(List<String> moveCommand) {
        List<String> endSource = List.of(moveCommand.get(END_SOURCE_INDEX).split(SOURCE_REGEX));
        columnOfEndSource = endSource.get(COLUMN_INDEX);
        rankOfEndSource = endSource.get(RANK_INDEX);
    }

    private void setStartPosition(List<String> moveCommand) {
        List<String> startSource = List.of(moveCommand.get(START_SOURCE_INDEX).split(SOURCE_REGEX));
        columnOfStartSource = startSource.get(COLUMN_INDEX);
        rankOfStartSource = startSource.get(RANK_INDEX);
    }

    public Command getCommand() {
        return command;
    }

    public String getColumnOfStartSource() {
        return columnOfStartSource;
    }

    public String getRankOfStartSource() {
        return rankOfStartSource;
    }

    public String getColumnOfEndSource() {
        return columnOfEndSource;
    }

    public String getRankOfEndSource() {
        return rankOfEndSource;
    }
}
