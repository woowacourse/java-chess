package controller.command;

public class Status implements Command {

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
