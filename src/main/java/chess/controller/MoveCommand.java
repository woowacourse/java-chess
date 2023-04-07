package chess.controller;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.List;

public class MoveCommand {

    private final Command command;
    private final List<String> options;

    public MoveCommand(final Command command) {
        this.command = command;
        this.options = command.getOptions();
    }

    public Position getSourcePosition() {
        return Position.of(getSourceRank(), getSourceFile());
    }

    private File getSourceFile() {
        final String fromFile = getFirstOption().substring(0, 1);
        return File.from(fromFile);
    }

    private Rank getSourceRank() {
        final String fromRank = getFirstOption().substring(1);
        return Rank.from(fromRank);
    }

    private String getFirstOption() {
        return options.get(0);
    }

    public Position getDestinationPosition() {
        return Position.of(getDestinationRank(), getDestinationFile());
    }

    private File getDestinationFile() {
        final String toFile = getSecondOption().substring(0, 1);
        return File.from(toFile);
    }

    private Rank getDestinationRank() {
        final String toRank = getSecondOption().substring(1);
        return Rank.from(toRank);
    }

    private String getSecondOption() {
        return options.get(1);
    }
}
