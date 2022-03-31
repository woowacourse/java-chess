package chess.view;

import chess.domain.position.Position;

public class CommandDto {

    private final Command command;
    private final Position from;
    private final Position to;

    private CommandDto(final Command command) {
        this.command = command;
        this.from = null;
        this.to = null;
    }

    private CommandDto(final Command command, final Position from, final Position to) {
        this.command = command;
        this.from = from;
        this.to = to;
    }

    public static CommandDto from(final String text) {
        final Command command = Command.from(text);
        if (command.equals(Command.MOVE)) {
            return new CommandDto(
                    command,
                    Command.findFromPosition(text),
                    Command.findToPosition(text)
            );
        }
        return new CommandDto(command);
    }

    public Command getCommand() {
        return command;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
