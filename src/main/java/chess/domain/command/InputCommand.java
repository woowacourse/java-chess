package chess.domain.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum InputCommand {
    MOVE("move"),
    START("start"),
    END("end"),
    STATUS("status");

    private List<String> value;

    InputCommand(String... value) {
        this.value = Arrays.asList(value);
    }

    public String getCommand () {
        return value.get(0);
    }

    public String getFirstPosition () {
        return value.get(1);
    }

    public String getSecondPosition () {
        return value.get(2);
    }
}
