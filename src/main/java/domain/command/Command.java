package domain.command;


public abstract class Command {
    public abstract Command next(Command command);

    public boolean isRunning() {
        return true;
    }

    public boolean isMove() {
        return false;
    }
}
