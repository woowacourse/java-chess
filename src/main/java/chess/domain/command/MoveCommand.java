package chess.domain.command;

import chess.domain.postion.Position;
import chess.domain.state.State;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MoveCommand extends Command {

    private static final String MOVE_DELIMITER = " ";
    private static final int MOVE_COMMEND_LENGTH = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int INPUT_POSITION_LENGTH = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final String command;

    public MoveCommand(final String input) {
        validateMoveCommand(input);

        this.command = input;
    }

    private static void validateMoveCommand(final String input) {
        List<String> move = Arrays.stream(input.split(MOVE_DELIMITER))
                .collect(Collectors.toList());

        if (move.size() != MOVE_COMMEND_LENGTH) {
            throw new IllegalArgumentException("move source위치 target위치 형식으로 입력해주세요.");
        }
    }

    public List<Position> makeSourceTargetPosition() {
        List<String> move = Arrays.stream(command.split(MOVE_DELIMITER))
                .collect(Collectors.toList());

        String sourceInput = move.get(SOURCE_INDEX);
        String targetInput = move.get(TARGET_INDEX);

        validatePositionInput(sourceInput);
        validatePositionInput(targetInput);

        return positions(sourceInput, targetInput);
    }

    private void validatePositionInput(final String input) {
        if (input.length() != INPUT_POSITION_LENGTH) {
            throw new IllegalArgumentException("위치는 file, rank 하나씩 입력되어야 합니다. (file: a~h, rank: 1~8)");
        }
    }

    private List<Position> positions(String sourceInput, String targetInput) {
        final int sourceFile = inputToPositionValue(sourceInput.charAt(FILE_INDEX));
        final int sourceRank = inputToPositionValue(sourceInput.charAt(RANK_INDEX));

        final int targetFile = inputToPositionValue(targetInput.charAt(FILE_INDEX));
        final int targetRank = inputToPositionValue(targetInput.charAt(RANK_INDEX));

        Position source = Position.of(sourceFile, sourceRank);
        Position target = Position.of(targetFile, targetRank);

        return List.of(source, target);
    }

    private int inputToFile(char input) {
        return input - '`';
    }

    private int inputToPositionValue(char input) {
        return Character.getNumericValue(input);
    }

    @Override
    public State changeChessState(final State state) {
        final List<Position> positions = makeSourceTargetPosition();
        return state.changeTurn(positions);
    }
}
