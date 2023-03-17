package chess.domain.state.command;

import chess.domain.piece.position.PiecePosition;

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
        final Type type = Type.find(typeInput);
        return new Command(type, inputs);
    }

    public List<PiecePosition> moveParameters() {
        final PiecePosition from = PiecePosition.of(commands.get(FROM_POSITION_INDEX));
        final PiecePosition to = PiecePosition.of(commands.get(TO_POSITION_INDEX));
        return Arrays.asList(from, to);
    }

    public boolean isStart() {
        return type == Type.START;
    }

    public boolean isEnd() {
        return type == Type.END;
    }
}
