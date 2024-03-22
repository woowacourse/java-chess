package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chess.view.command.CommandType;
import chess.view.command.InitialCommand;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public InitialCommand askCommand() {
        String input = input();
        return new InitialCommand(CommandType.of(input), input);
    }

    private String input() {
        try {
            return READER.readLine();
        } catch (IOException ignored) {
        }
        return "";
    }
}
