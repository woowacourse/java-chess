package view;

import controller.command.Command;
import controller.command.EndOnCommand;
import controller.command.MoveOnCommand;
import controller.command.StartOnCommand;
import domain.position.Position;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Command readCommand() {
        String[] rawInput = scanner.nextLine().trim().split(" ");
        if ("start".equals(rawInput[0])) {
            return new StartOnCommand();
        }
        if ("move".equals(rawInput[0])) {
            return new MoveOnCommand(resolvePosition(rawInput[1]), resolvePosition(rawInput[2]));
        }
        if ("end".equals(rawInput[0])) {
            return new EndOnCommand();
        }
        throw new IllegalArgumentException("잘못된 명령어입니다");
    }

    private Position resolvePosition(final String position) {
        return new Position(position.substring(0, 1), position.substring(1));
    }
}
