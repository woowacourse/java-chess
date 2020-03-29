package chess.domain;

import chess.domain.board.Position;
import chess.domain.board.Positions;

import static chess.util.NullValidator.validateNull;

public class MoveCommand {
    private static final String MOVE_COMMAND = "move";

    private final Position sourcePosition;
    private final Position targetPosition;

    public MoveCommand(String moveCommand) {
        validate(moveCommand);
        String[] separatedCommand = moveCommand.split(" ");
        sourcePosition = Positions.of(separatedCommand[1]);
        targetPosition = Positions.of(separatedCommand[2]);
    }

    private void validate(String moveCommand) {
        validateNull(moveCommand);
        String[] commandWords = moveCommand.split(" ");
        validateCommandWord(commandWords[0]);
        validatePositionWord(commandWords[1]);
        validatePositionWord(commandWords[2]);
    }

    private void validateCommandWord(String commandWord) {
        if (!MOVE_COMMAND.equals(commandWord)) {
            throw new IllegalArgumentException("잘못된 명령어가 입력되었습니다.");
        }
    }

    private void validatePositionWord(String position) {
        Positions.of(position);
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }
}
