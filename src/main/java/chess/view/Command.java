package chess.view;

import java.util.List;

public final class Command {
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
        if (input.size() == 3) {
            return new Command(input.get(0), input.get(1), input.get(2));
        }

        return new Command(input.get(0));
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
