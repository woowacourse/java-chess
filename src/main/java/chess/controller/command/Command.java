package chess.controller.command;

import chess.domain.piece.position.PiecePosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    public static final int FROM_POSITION_INDEX = 0;
    public static final int TO_POSITION_INDEX = 1;
    private static final int COMMAND_TYPE_INDEX = 0;

    private final CommandType commandType;
    private final List<String> commands;

    private Command(final CommandType commandType, final List<String> commands) {
        validateParameterSize(commandType, commands);
        this.commandType = commandType;
        this.commands = commands;
    }

    private void validateParameterSize(final CommandType commandType, final List<String> commands) {
        if (!commandType.matchSize(commands)) {
            throw new IllegalArgumentException(commandType + " 명령어의 파라미터가 올바르지 않습니다.");
        }
    }

    public static Command parse(List<String> inputs) {
        inputs = new ArrayList<>(inputs);
        final String typeInput = inputs.remove(COMMAND_TYPE_INDEX);
        final CommandType commandType = CommandType.find(typeInput);
        return new Command(commandType, inputs);
    }

    public List<PiecePosition> moveParameters() {
        final PiecePosition from = PiecePosition.of(commands.get(FROM_POSITION_INDEX));
        final PiecePosition to = PiecePosition.of(commands.get(TO_POSITION_INDEX));
        return Arrays.asList(from, to);
    }

    public Long restartParameter() {
        return Long.parseLong(commands.get(0));
    }

    public CommandType type() {
        return commandType;
    }
}
