package view.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CommandType {
    START("start"),
    END("end"),
    MOVE("move"),
    ;

    private final String command;
    private final List<String> supplements;

    CommandType(final String command) {
        this.command = command;
        this.supplements = new ArrayList<>();
    }

    public static CommandType from(final SeparatedCommandInput separatedCommandInput) {
        CommandType commandType = Arrays.stream(CommandType.values())
                .filter(type -> type.command.equals(separatedCommandInput.prefix()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR]잘못된 게임 명령어입니다."));
        commandType.addSupplements(separatedCommandInput.getSupplements());
        return commandType;
    }

    public void addSupplements(final List<String> supplements) {
        this.supplements.clear();
        this.supplements.addAll(supplements);
    }

    public boolean hasSupplements() {
        return !supplements.isEmpty();
    }

    public List<String> getSupplements() {
        return supplements;
    }

    public boolean notEqualsSupplementSize(final int size) {
        return supplements.size() != size;
    }
}
