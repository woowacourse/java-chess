package domain.command;

import java.util.List;

public abstract class Command {
    protected final List<String> options;

    public Command(List<String> options) {
        this.options = options;
    }

    public abstract <T> List<T> getOptions();
}
