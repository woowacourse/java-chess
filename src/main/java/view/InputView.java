package view;

import controller.command.Command;
import controller.command.StartOnCommand;

import java.util.Scanner;

public class InputView {
    public static final String WRONG_COMMAND_ERROR_MESSAGE = "잘못된 형식의 명령어입니다.";
    public static final String GAME_NOT_STARTED_ERROR_MESSAGE = "먼저, 게임을 시작해 주세요.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Command readStartCommand() {
        String[] tokens = readUserInput();
        if (tokens.length == 1 && CommandType.START.name().equals(tokens[0])) {
            return new StartOnCommand();
        }
        throw new IllegalArgumentException(GAME_NOT_STARTED_ERROR_MESSAGE);
    }

    public Command readCommand() {
        String[] tokens = readUserInput();
        try {
            validateCommand(tokens);
            return CommandType.getCommand(tokens);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(WRONG_COMMAND_ERROR_MESSAGE);
        }
    }

    private void validateCommand(String[] tokens) {
        for (int i = 1; i < tokens.length; i++) {
            validatePositionFormat(tokens[i]);
        }
    }

    private void validatePositionFormat(String token) {
        if (token.length() != 2) {
            throw new IllegalArgumentException();
        }
        try {
            Integer.parseInt(token.substring(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private String[] readUserInput() {
        return scanner.nextLine().trim().toUpperCase().split(" ");
    }
}
