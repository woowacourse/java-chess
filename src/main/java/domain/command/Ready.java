package domain.command;

public final class Ready extends Command {

    private static final Command instance = new Ready();

    private Ready() {
    }

    public static Command getInstance() {
        return instance;
    }

    @Override
    public Command next(final Command command) {
        if (command == Move.getInstance()) {
            throw new UnsupportedOperationException("준비 상태에서 움직임 상태로 넘어갈 수 없습니다");
        }
        return command;
    }
}
