package domain.command;

public class StartCommand implements Command {
    public static StartCommand START_COMMAND = new StartCommand();

    private StartCommand() {
    }
}
