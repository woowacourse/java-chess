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

    public Command readStartCommand() {
        String[] rawInput = scanner.nextLine().trim().split(" ");
        if (hasSize(rawInput, 1) && "start".equals(rawInput[0])) {
            return new StartOnCommand();
        }
        throw new IllegalArgumentException("먼저, 게임을 시작해 주세요.");
    }

    public Command readCommand() {
        String[] rawInput = scanner.nextLine().trim().split(" ");
        if (hasSize(rawInput, 1) && "start".equals(rawInput[0])) {
            return new StartOnCommand();
        }
        if (hasSize(rawInput, 3) && "move".equals(rawInput[0])) {
            return new MoveOnCommand(resolvePosition(rawInput[1]), resolvePosition(rawInput[2]));
        }
        if (hasSize(rawInput, 1) && "end".equals(rawInput[0])) {
            return new EndOnCommand();
        }
        throw new IllegalArgumentException("잘못된 형식의 명령어입니다.");
    }

    private Position resolvePosition(String position) {
        String file = position.substring(0, 1);
        String rank = position.substring(1);
        try {
            return new Position(file, Integer.parseInt(rank));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 형식의 명령어입니다.");
        }
    }

    private boolean hasSize(final String[] rawInput, int size) {
        return rawInput.length == size;
    }
}
