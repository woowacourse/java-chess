package chess.view;

import static chess.exception.ExceptionHandler.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chess.view.command.Command;
import chess.view.command.CommandType;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public Command askCommand() {
        return retry(() -> {
            String input = input();
            return new Command(CommandType.of(input), input);
        });
    }

    private String input() {
        try {
            return READER.readLine();
        } catch (IOException ignored) {
        }
        return "";
    }
}
