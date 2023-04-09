package view;

import controller.command.Command;
import controller.command.YNCommand;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public Command getGameCommand() {
        return new Command(scanner.nextLine());
    }

    public String getGameId() {
        return scanner.nextLine();
    }

    public YNCommand getYNCommand() {
        return YNCommand.from(scanner.nextLine());
    }
}
