package controller.command;

public class SystemEnd implements Command {

    @Override
    public Command execute() {
        return this;
    }

    @Override
    public Command readNextCommand() {
        return this;
    }

    public boolean isEnd() {
        return true;
    }
}
