package controller.command;

public interface Command {

    Command execute();

    Command readNextCommand();

    boolean isEnd();
}
