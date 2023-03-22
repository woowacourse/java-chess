package chess.controller;

import chess.chessboard.File;
import chess.chessboard.Rank;

import java.util.List;

public class MoveCommandDto implements CommandDto {

    private static final String COMMAND = "move";

    private final List<String> options;

    private MoveCommandDto(final List<String> options) {
        this.options = options;
    }

    public static CommandDto from(final List<String> commandWithOptions) {
        final int optionBeginIndex = 1;
        final int optionEndIndex = commandWithOptions.size();
        final List<String> options = commandWithOptions.subList(optionBeginIndex, optionEndIndex);
        return new MoveCommandDto(options);
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

    public File getSourceFile() {
        final String sourceFile = getSourceSquare().substring(0, 1);
        return File.from(sourceFile);
    }

    public Rank getSourceRank() {
        final String sourceRank = getSourceSquare().substring(1);
        return Rank.from(sourceRank);
    }

    private String getSourceSquare() {
        return options.get(0);
    }

    public File getDestinationFile() {
        final String destinationFile = getDestinationSquare().substring(0, 1);
        return File.from(destinationFile);
    }

    public Rank getDestinationRank() {
        final String destinationRank = getDestinationSquare().substring(1);
        return Rank.from(destinationRank);
    }

    private String getDestinationSquare() {
        return options.get(1);
    }
}
