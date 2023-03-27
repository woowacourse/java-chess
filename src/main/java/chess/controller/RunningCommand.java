package chess.controller;

import java.util.ArrayList;
import java.util.List;

public class RunningCommand {

    private final List<String> parameters;
    private final RunningCommandType runningCommandType;

    private RunningCommand(final List<String> parameters, final RunningCommandType runningCommandType) {
        this.parameters = parameters;
        this.runningCommandType = runningCommandType;
    }

    public static RunningCommand parse(List<String> commands) {
        commands = new ArrayList<>(commands);
        final RunningCommandType type = RunningCommandType.from(commands.remove(0));
        type.validateParameterSize(commands);
        return new RunningCommand(commands, type);
    }

    public List<String> getParameters() {
        return parameters;
    }

    public RunningCommandType getRunningCommandType() {
        return runningCommandType;
    }
}
