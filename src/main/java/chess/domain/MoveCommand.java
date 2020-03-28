package chess.domain;

import chess.domain.board.Position;

import static chess.util.NullValidator.validateNull;

public class MoveCommand {
    private static final String MOVE_COMMAND = "move";
    private static final char MIN_CHARACTER = 'a';
    private static final char MAX_CHARACTER = 'h';
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 8;
    private static final String WRONG_POSITION_INPUT_MESSAGE = "잘못된 형태의 위치가 입력되었습니다.";
    private static final int MAX_WORD_LENGTH = 2;

    private final Position sourcePosition;
    private final Position targetPosition;

    public MoveCommand(String moveCommand) {
        validate(moveCommand);
        String[] separatedCommand = moveCommand.split(" ");
        sourcePosition = new Position(separatedCommand[1]);
        targetPosition = new Position(separatedCommand[2]);
    }

    private void validate(String moveCommand) {
        validateNull(moveCommand);
        String[] commandWords = moveCommand.split(" ");
        validateCommandWord(commandWords[0]);
        validatePositionWord(commandWords[1], commandWords[2]);
    }

    private void validateCommandWord(String commandWord) {
        if (!MOVE_COMMAND.equals(commandWord)) {
            throw new IllegalArgumentException("잘못된 명령어가 입력되었습니다.");
        }
    }

    private void validatePositionWord(String sourcePosition, String targetPosition) {
        validatePositionWord(sourcePosition);
        validatePositionWord(targetPosition);
    }

    private void validatePositionWord(String position) {
        validateWordLength(position);
        validateFirstCharacter(position.charAt(0));
        validateSecondCharacter(position.charAt(1));
    }

    private void validateWordLength(String position) {
        if (MAX_WORD_LENGTH < position.length()) {
            throw new IllegalArgumentException(WRONG_POSITION_INPUT_MESSAGE);
        }
    }

    private void validateFirstCharacter(char firstCharacter) {
        if (firstCharacter < MIN_CHARACTER || MAX_CHARACTER < firstCharacter) {
            throw new IllegalArgumentException(WRONG_POSITION_INPUT_MESSAGE);
        }
    }

    private void validateSecondCharacter(char secondCharacter) {
        int secondCharacterValue;
        try {
            secondCharacterValue = Integer.parseInt(String.valueOf(secondCharacter));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(WRONG_POSITION_INPUT_MESSAGE);
        }

        if (secondCharacterValue < MIN_NUMBER || MAX_NUMBER < secondCharacterValue) {
            throw new IllegalArgumentException(WRONG_POSITION_INPUT_MESSAGE);
        }
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }
}
