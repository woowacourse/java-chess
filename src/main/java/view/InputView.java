package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private InputView() {
    }

    public static GameCommand inputGameCommand() {
        String inputCommand = readLine();
        return GameCommand.from(inputCommand);
    }

    private static String readLine() {
        try {
            return READER.readLine();
        } catch (final IOException exception) {
            System.out.println(exception.getMessage());
            return readLine();
        }
    }
}
