package view.command;

import java.util.List;

public class SeparatedCommandInput {
    private static final String COMMAND_SEPARATOR = " ";
    private final List<String> separated;

    private SeparatedCommandInput(final List<String> separated) {
        this.separated = separated;
    }

    public static SeparatedCommandInput from(final String command) {
        return new SeparatedCommandInput(List.of(command.split(COMMAND_SEPARATOR)));
    }

    public String prefix() {
        return separated.get(0);
    }

    public List<String> getSupplements() {
        return separated.subList(1, separated.size());
    }
}
