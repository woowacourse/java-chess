package controller.command;

public class Continue implements Command {
    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public boolean isSearch() {
        return false;
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
