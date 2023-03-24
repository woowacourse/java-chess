package controller.command;

public class Start implements Command {
    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
