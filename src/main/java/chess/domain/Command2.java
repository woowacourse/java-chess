package chess.domain;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command2 {

    private final List<String> inputs;

    private Command2(List<String> inputs) {
        this.inputs = new ArrayList<>(inputs);
    }

    public static Command2 of(String input) {
        List<String> separatedInputs = Arrays.asList(input.split(" "));
        validate(separatedInputs);
        return new Command2(separatedInputs);
    }

    private static void validate(List<String> separatedInputs) {
//        Command.validate(separatedInputs.get(0));
//        Position.validate(separatedInputs.get(1));
//        Position.validate(separatedInputs.get(2));
    }

    public boolean isStart() {
        return false;
    }

    public boolean isEnd() {
        return false;
    }
}
