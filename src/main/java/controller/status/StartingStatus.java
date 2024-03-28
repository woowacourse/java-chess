package controller.status;

import view.InputView;

public class StartingStatus implements ChessProgramStatus {

    @Override
    public String readCommand() {
        return InputView.readCommand();
    }

    @Override
    public int gameId() {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public boolean isStarting() {
        return true;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
