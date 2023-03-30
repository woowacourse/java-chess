package controller.command;

public class Search implements Command {
    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isSearch() {
        return true;
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
        return true;
    }

    @Override
    public boolean isContinue() {
        return false;
    }
}
