package controller.command;

public class SystemEnd extends Command {

    protected SystemEnd() {
    }

    @Override
    public Command execute() {
        return this;
    }

    @Override
    protected Command readNextCommand() {
        return this;
    }

    public boolean isEnd() {
        return true;
    }
}
