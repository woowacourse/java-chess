package controller.command;

public abstract class Command {

    public abstract Command execute();

    protected abstract Command readNextCommand();

    public abstract boolean isEnd();
}
