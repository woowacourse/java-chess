package chess.view;

import chess.domain.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandExpression {

    private final Command gameCommand;
    private final List<Position> positions;

    private CommandExpression(Command gameCommand, List<Position> positions) {
        this.gameCommand = gameCommand;
        this.positions = positions;
    }

    public static CommandExpression of(String rawInput) {
        List<String> parsedInput = Arrays.stream(rawInput.split(" ")).toList();
        String commandInput = parsedInput.get(0);
        Command gameCommand = Command.of(commandInput);
        validateCommandArgumentsSize(gameCommand, parsedInput);

        List<Position> positions = new ArrayList<>();
        if (gameCommand == Command.MOVE) {
            positions.addAll(makePositions(parsedInput));
        }

        return new CommandExpression(gameCommand, positions);
    }

    private static void validateCommandArgumentsSize(Command gameCommand, List<String> commands) {
        if ((gameCommand == Command.START && commands.size() != 1) ||
                (gameCommand == Command.END && commands.size() != 1) ||
                (gameCommand == Command.MOVE && commands.size() != 3)
        ) {
            throw new IllegalArgumentException("명령어에 맞는 인자의 갯수가 아닙니다.");
        }
    }

    private static List<Position> makePositions(List<String> parsedInput) {
        List<Position> positions = new ArrayList<>();
        String sourcePositionInput = parsedInput.get(1);
        Position sourcePosition = PositionExpression.mapToPosition(sourcePositionInput);
        positions.add(sourcePosition);

        String targetPositionInput = parsedInput.get(2);
        Position targetPosition = PositionExpression.mapToPosition(targetPositionInput);
        positions.add(targetPosition);

        return positions;
    }

    public boolean isStart() {
        return this.gameCommand == Command.START;
    }

    public boolean isMove() {
        return this.gameCommand == Command.MOVE;
    }

    public boolean isEnd() {
        return this.gameCommand == Command.END;
    }

    public Position getSourcePosition() {
        return positions.get(0);
    }

    public Position getTargetPosition() {
        return positions.get(1);
    }
}
