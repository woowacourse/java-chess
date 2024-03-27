package view.command;

import java.util.List;

public class SeparatedInput {
    private static final String COMMAND_SEPARATOR = " ";
    private final List<String> separated;

    private SeparatedInput(final List<String> separated) {
        this.separated = separated;
    }

    public static SeparatedInput from(final String command) {
        return new SeparatedInput(List.of(command.split(COMMAND_SEPARATOR)));
    }

    public String prefix() {
        return separated.get(0);
    }

    public List<String> getSupplements() {
        return separated.subList(1, separated.size());
    }
}
