package chess.view;

import chess.domain.Command;
import chess.exception.InvalidCoordinateException;
import chess.exception.WrongCommandException;

import java.util.Locale;
import java.util.Scanner;

public final class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final int POINT_LENGTH = 2;

    private InputView() {
    }

    public static String command() {
        try {
            String command = SCANNER.next().toUpperCase(Locale.ROOT);
            validatesCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return command();
        }
    }

    private static void validatesCommand(final String command) {
        if (!Command.validatesCommand(command)) {
            throw new WrongCommandException();
        }
    }

    public static String point() {
        String point = SCANNER.next().toLowerCase(Locale.ROOT);
        validatesPoint(point);
        return point;
    }

    private static void validatesPoint(final String point) {
        if (point.length() != POINT_LENGTH) {
            throw new InvalidCoordinateException();
        }
    }
}