package chess.domain.state.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    public static final int FROM_POSITION_INDEX = 0;
    public static final int TO_POSITION_INDEX = 1;
    private static final int COMMAND_TYPE_INDEX = 0;

    private final Type type;
    private final List<String> commands;

    private Command(final Type type, final List<String> commands) {
        validateParameterSize(type, commands);
        this.type = type;
        this.commands = commands;
    }

    private void validateParameterSize(final Type type, final List<String> commands) {
        if (!type.matchSize(commands)) {
            throw new IllegalArgumentException(type + " 명령어의 파라미터가 올바르지 않습니다.");
        }
    }

    public static Command parse(List<String> inputs) {
        inputs = new ArrayList<>(inputs);
        final String typeInput = inputs.remove(COMMAND_TYPE_INDEX);
        final Type type = Arrays.stream(Type.values())
                .filter(it -> it.name().equalsIgnoreCase(typeInput))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 커맨드입니다."));
        return new Command(type, inputs);
    }

    public List<String> parameters() {
        return commands;
    }

    public boolean isStart() {
        return type == Type.START;
    }

    public boolean isEnd() {
        return type == Type.END;
    }
}
