package view;

import controller.Command;
import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.Scanner;
import view.mapper.CommandInput;

public class InputView {

    private static final int POSITION_LENGTH = 2;
    private static final int POSITION_FILE_INDEX = 0;
    private static final int POSITION_RANK_INDEX = 1;

    private final Scanner scanner = new Scanner(System.in);

    public Command readInitCommand() {
        String rawCommand = scanner.nextLine();
        return CommandInput.asCommand(rawCommand);
    }

    public Command readCommand() {
        String rawCommand = scanner.next();
        return CommandInput.asCommand(rawCommand);
    }

    public Position readPosition() {
        String rawPosition = scanner.next();
        validatePositionLength(rawPosition);
        String rawFile = String.valueOf(rawPosition.charAt(POSITION_FILE_INDEX));
        String rawRank = String.valueOf(rawPosition.charAt(POSITION_RANK_INDEX));
        return PositionGenerator.generate(rawFile, rawRank);
    }

    private void validatePositionLength(String rawPosition) {
        if (rawPosition.length() != POSITION_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 올바른 위치를 입력해주세요.");
        }
    }

    public void clean() {
        scanner.nextLine();
    }
}
