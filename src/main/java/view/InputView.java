package view;

import domain.command.Command;
import java.util.Arrays;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Command readCommand() {
        String input = scanner.nextLine();
        try {
            return CommandInput.asCommand(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readCommand();
        }
    }

    private enum CommandInput {

        START(Command.START, "start"),
        MOVE(Command.MOVE, "move"),
        END(Command.END, "end");

        private final Command command;
        private final String input;

        CommandInput(Command command, String input) {
            this.command = command;
            this.input = input;
        }

        private static Command asCommand(String input) {
            return Arrays.stream(values())
                    .filter(commandInput -> commandInput.input.equals(input))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 명령어를 입력해주세요."))
                    .command;
        }
    }
}
