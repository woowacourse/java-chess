package chess.view;

import java.util.List;

public final class Command {
    private static final int THREE_FOR_MOVE_COMMAND = 3;
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final String action;
    private final String from;
    private final String to;

    public Command(String action, String from, String to) {
        this.action = action;
        this.from = from;
        this.to = to;
    }

    public Command(String action) {
        this(action, "", "");
    }

    public static Command from(List<String> input) {
        if (input.size() == THREE_FOR_MOVE_COMMAND) {
            return new Command(input.get(COMMAND_INDEX), input.get(SOURCE_INDEX), input.get(TARGET_INDEX));
        }

        return new Command(input.get(COMMAND_INDEX));
    }

    public boolean isStart() {
        return "start".equalsIgnoreCase(this.action);
    }

    public boolean isEnd() {
        return "end".equalsIgnoreCase(this.action);
    }

    public boolean isMove() {
        return "move".equalsIgnoreCase(this.action);
    }

    public boolean isStatus() {
        return "status".equalsIgnoreCase(this.action);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
