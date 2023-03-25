package chess.controller.command;

import chess.domain.piece.position.PiecePosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunningCommand {

    public static final int FROM_POSITION_INDEX = 0;
    public static final int TO_POSITION_INDEX = 1;
    private static final int COMMAND_TYPE_INDEX = 0;

    private final RunningCommandType runningCommandType;
    private final List<String> commands;

    private RunningCommand(final RunningCommandType runningCommandType, final List<String> commands) {
        validateParameterSize(runningCommandType, commands);
        this.runningCommandType = runningCommandType;
        this.commands = commands;
    }

    private void validateParameterSize(final RunningCommandType startCommandType, final List<String> commands) {
        if (!startCommandType.matchSize(commands)) {
            throw new IllegalArgumentException(startCommandType + " 명령어의 파라미터가 올바르지 않습니다.");
        }
    }

    public static RunningCommand parse(List<String> inputs) {
        inputs = new ArrayList<>(inputs);
        final String typeInput = inputs.remove(COMMAND_TYPE_INDEX);
        final RunningCommandType runningCommandType = RunningCommandType.find(typeInput);
        return new RunningCommand(runningCommandType, inputs);
    }

    public List<PiecePosition> moveParameters() {
        final PiecePosition from = PiecePosition.of(commands.get(FROM_POSITION_INDEX));
        final PiecePosition to = PiecePosition.of(commands.get(TO_POSITION_INDEX));
        return Arrays.asList(from, to);
    }

    public RunningCommandType type() {
        return runningCommandType;
    }
}
