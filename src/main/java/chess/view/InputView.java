package chess.view;

import chess.dto.PositionDTO;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int POSITION_LENGTH = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int FILE_START_ASCII = 'a' - 1;
    private static final int RANK_START_ASCII = '1' - 1;

    public Command askStartCommand() {
        String input = SCANNER.next();
        Command command = Command.findBy(input);
        validateStartCommand(command);
        return command;
    }

    private void validateStartCommand(Command command) {
        if (command != Command.START) {
            throw new IllegalArgumentException("게임을 시작하려면 start를 입력해야 합니다.");
        }
    }

    public Command askMoveOrEndCommand() {
        String input = SCANNER.next();
        Command command = Command.findBy(input);
        validateMoveOrEndCommand(command);
        return command;
    }

    private void validateMoveOrEndCommand(Command command) {
        if (command != Command.MOVE && command != Command.END) {
            throw new IllegalArgumentException("게임을 이미 시작한 상태에선 move 또는 end를 입력해야 합니다.");
        }
    }

    public PositionDTO askPosition() {
        String input = SCANNER.next();
        validatePositionLength(input);
        return convertToPosition(input);
    }

    private void validatePositionLength(String input) {
        if (input.length() != POSITION_LENGTH) {
            throw new IllegalArgumentException("이동할 위치는 b2 꼴로 입력해야 합니다.");
        }
    }

    private PositionDTO convertToPosition(String input) {
        int file = input.charAt(FILE_INDEX) - FILE_START_ASCII;
        int rank = input.charAt(RANK_INDEX) - RANK_START_ASCII;
        return new PositionDTO(file, rank);
    }
}
