package chess.domain;

import chess.domain.position.Position2;

public final class CommandAsString {

    private final String[] commandInputs;

    public CommandAsString(final String stringCommand) {
        this(stringCommand.split(" "));
    }

    private CommandAsString(final String[] commandInputs) {
        this.commandInputs = commandInputs;
    }

    public Command2 command() {
        return Command2.valueOf(commandInputs[0].toUpperCase());
    }

    public Position2 source() {
        return positionOfCommand(1);
    }

    public Position2 target() {
        return positionOfCommand(2);
    }

    private Position2 positionOfCommand(int number) {
        if (commandInputs.length == 1) {
            throw new IllegalArgumentException("플레이어의 행동이 아닙니다.");
        }
        return Position2.ofName(commandInputs[number]);
    }
}
