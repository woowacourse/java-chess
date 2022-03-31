package chess.domain.command;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;

public class CommandConverter {

    private static final String INVALID_INPUT = "유효한 입력이 아닙니다.";

    public static Command convertCommand(List<String> input) {
        validateCommonCondition(input);
        return generateCommand(input);
    }

    private static void validateCommonCondition(List<String> input) {
        if (Arrays.stream(CommandConditions.values()).noneMatch(each -> each.doesSatisfy(input))) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    private static Command generateCommand(List<String> input) {
        if (input.size() == 1) {
            return SingleCommand.from(input);
        }
        return new MoveCommand(new Position(input.get(1)), new Position(input.get(2)));
    }
}
