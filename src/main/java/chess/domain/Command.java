package chess.domain;

import java.util.Arrays;
import java.util.List;

public class Command {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";

    private final String[] values;

    public Command(String values) {
        this.values = splitCommand(values);
        validateCommand(this.values);
    }

    private void validateCommand(String[] values) {
        String command = values[0];
        if (!(START.equals(command) || END.equals(command) || MOVE.equals(command))) {
            throw new IllegalArgumentException("[ERROR]");
        }
    }

    private String[] splitCommand(String input) {
        return input.split(" ");
    }

    public void validateRightFirstCommand() {
        if (!(values[0].equals(START) || values[0].equals(END))) {
            throw new IllegalArgumentException("[ERROR] 올바른 첫 명령어가 아닙니다.");
        }
    }

    public boolean isMove() {
        return MOVE.equals(values[0]);
    }

    public List<String> getSourceTarget() {
        return Arrays.asList(values[1], values[2]);
    }

    public boolean isStart() {
        return values[0].equals(START);
    }

    public boolean isEnd() {
        return values[0].equals(END);
    }

    public void validateRunningCommand() {
        if (!(values[0].equals(END) || values[0].equals(MOVE))) {
            throw new IllegalArgumentException("[ERROR] 올바른 첫 명령어가 아닙니다.");
        }
    }
}
