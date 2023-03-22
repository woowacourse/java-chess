package chess.domain.state.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    public static final int FROM_POSITION_INDEX = 0;
    public static final int TO_POSITION_INDEX = 1;

    private final Type type;
    private final List<String> commands;

    private Command(final Type type, final List<String> commands) {
        validateParameterSize(type, commands);
        this.type = type;
        this.commands = new ArrayList<>(commands);
    }

    private void validateParameterSize(final Type type, final List<String> commands) {
        if (!type.matchSize(commands)) {
            throw new IllegalArgumentException(type + " 명령어의 파라미터가 올바르지 않습니다.");
        }
    }

    public static Command parse(final List<String> inputs) {
        final String typeInput = inputs.remove(0);
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
