package chess.controller;

import chess.chessboard.File;
import chess.chessboard.Rank;
import chess.chessboard.Square;

import java.util.List;
import java.util.Objects;

public class MoveCommand implements Command {

    private final List<String> options;

    private MoveCommand(final List<String> options) {
        this.options = options;
    }

    public static Command from(final List<String> commandWithOptions) {
        final String command = commandWithOptions.get(0);
        validateCommandIsMove(command);

        final int optionBeginIndex = 1;
        final int optionEndIndex = commandWithOptions.size();

        final List<String> options = commandWithOptions.subList(optionBeginIndex, optionEndIndex);

        return new MoveCommand(options);
    }

    private static void validateCommandIsMove(final String command) {
        if (!Objects.equals(command, "move")) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
    }

    public Square getSourceSquare() {
        return Square.of(getSourceRank(), getSourceFile());
    }

    private File getSourceFile() {
        final String sourceFile = getFirstOption().substring(0, 1);
        return File.from(sourceFile);
    }

    private Rank getSourceRank() {
        final String sourceRank = getFirstOption().substring(1);
        return Rank.from(sourceRank);
    }

    private String getFirstOption() {
        return options.get(0);
    }

    public Square getDestinationSquare() {
        return Square.of(getDestinationRank(), getDestinationFile());
    }

    private File getDestinationFile() {
        final String destinationFile = getSecondOption().substring(0, 1);
        return File.from(destinationFile);
    }

    private Rank getDestinationRank() {
        final String destinationRank = getSecondOption().substring(1);
        return Rank.from(destinationRank);
    }

    private String getSecondOption() {
        return options.get(1);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.MOVE;
    }
}
