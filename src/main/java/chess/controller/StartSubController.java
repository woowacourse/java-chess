package chess.controller;

public class StartSubController implements SubController {

    @Override
    public void run(boolean isStart) {
        if (isStart) {
            throw new IllegalArgumentException("이미 게임이 시작되었습니다.");
        }
    }
}
