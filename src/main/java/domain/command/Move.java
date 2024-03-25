package domain.command;

public final class Move extends Command {

    private static final Command instance = new Move();

    private Move() {
    }

    @Override
    public Command next(final Command command) {
        if (command == Start.getInstance()) {
            throw new UnsupportedOperationException("움직임 상태에서 시작 상태로 넘어갈 수 없습니다");
        }
        return command;
    }

    @Override
    public boolean isMove() {
        return true;
    }

    public static Command getInstance() {
        return instance;
    }
}
