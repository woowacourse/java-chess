package view;

import domain.GameCommand;

import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static GameCommand readGameCommand() {
        return GameCommand.of(scanner.nextLine());
    }
}
