package chess.view;

import chess.exception.InvalidCoordinateException;
import chess.exception.WrongCommandException;

import java.util.Locale;
import java.util.Scanner;

public final class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final int POINT_LENGTH = 2;

    private InputView() {
    }

    public static String getCommand() {
        try {
            String command = SCANNER.next().toUpperCase(Locale.ROOT);
            validateCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getCommand();
        }
    }

    public static String getPoint() {
        String point = SCANNER.next().toLowerCase(Locale.ROOT);
        validatePoint(point);
        return point;
    }

    private static void validateCommand(final String command) {
        if (!Command.isValidateCommand(command)) {
            throw new WrongCommandException();
        }
    }

    private static void validatePoint(final String point) {
        if (point.length() != POINT_LENGTH) {
            throw new InvalidCoordinateException();
        }
    }
}