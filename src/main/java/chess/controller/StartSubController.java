package chess.controller;

public class StartSubController implements SubController {

    private boolean isStart = false;

    @Override
    public void run() {
        validateStartCommand();
        isStart = true;
    }

    private void validateStartCommand() {
        if (isStart) {
            throw new IllegalArgumentException("이미 게임이 시작되었습니다.");
        }
    }
}
