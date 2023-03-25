package chess.controller;

import static chess.controller.CommandActionMapper.START;
import static chess.controller.CommandActionMapper.STATUS;

public enum GameStatus {

    READY,
    RUNNING,
    OVER;

    public void validateCommand(String command) {
        if (this != OVER && isStatus(command)) {
            throw new IllegalArgumentException("아직 게임이 끝나지 않았습니다.");
        }
        if (this == READY && (!isStart(command))) {
            throw new IllegalArgumentException("아직 게임이 실행중이지 않습니다.");
        }
        if (this != READY && (isStart(command))) {
            throw new IllegalArgumentException("이미 게임이 실행중입니다.");
        }
    }

    private boolean isStatus(String command) {
        return STATUS.getCommand().equalsIgnoreCase(command);
    }

    private boolean isStart(String command) {
        return START.getCommand().equalsIgnoreCase(command);
    }
}
