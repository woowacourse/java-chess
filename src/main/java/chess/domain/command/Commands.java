package chess.domain.command;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Commands {

    private final String command;
    private final List<Position> path;

    public Commands(String command, List<Position> path) {
        this.command = command;
        this.path = path;
    }

    public Commands(String command) {
        this.command = makeCommand(command);
        this.path = makePath(command);
    }

    private String makeCommand(String command) {
        return command.split(" ")[0];
    }

    private List<Position> makePath(String command) {
        final String[] splitCommands = command.split(" ");
        if (validate(splitCommands))
            return convertToPosition(new ArrayList<>(Arrays.asList(splitCommands).subList(1, splitCommands.length)));
        return new ArrayList<>();
    }

    private boolean validate(String[] splitCommands) {
        return splitCommands.length > 1;
    }

    private List<Position> convertToPosition(List<String> positions) {
        return positions.stream()
                .map(position -> Position.of(convertFileToCoordinate(position), convertRankToCoordinate(position)))
                .collect(Collectors.toList());
    }

    private Rank convertRankToCoordinate(String coordinate) {
        return Rank.of(Integer.parseInt(String.valueOf(coordinate.charAt(1))));
    }

    private File convertFileToCoordinate(String coordinate) {
        return File.of(String.valueOf(coordinate.charAt(0)));
    }

    public String mainCommand() {
        return command;
    }

    public List<Position> path() {
        return path;
    }
}
