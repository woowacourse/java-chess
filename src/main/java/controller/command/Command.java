package controller.command;

public interface Command {
    boolean isStart();

    boolean isMove();

    boolean isEnd();

    boolean isStatus();

    boolean isSearch();

    boolean isContinue();
}
