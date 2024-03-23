package view;

import domain.command.Command;
import domain.position.Position;
import java.util.Scanner;
import view.mapper.CommandInput;

public class InputView {

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
        String rawFile = String.valueOf(rawPosition.charAt(0));
        String rawRank = String.valueOf(rawPosition.charAt(1));
        return Position.generate(rawFile, rawRank);
    }

    private void validatePositionLength(String rawPosition) {
        if (rawPosition.length() != 2) {
            throw new IllegalArgumentException("[ERROR] 올바른 위치를 입력해주세요.");
        }
    }

    public void clean() {
        scanner.nextLine();
    }
}
