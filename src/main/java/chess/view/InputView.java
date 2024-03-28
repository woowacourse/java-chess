package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public String askCommand() {
        return input();
    }

    private String input() {
        try {
            return READER.readLine();
        } catch (IOException ignored) {
        }
        return "";
    }
}
