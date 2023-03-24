package chess.controller;

import static chess.controller.CommandActionMapper.START;

public enum GameStatus {

    READY,
    RUNNING;

    public void validateCommand(String command) {
        if (this == READY && (!isStart(command))) {
            throw new IllegalArgumentException("아직 게임이 실행중이지 않습니다.");
        }
        if (this != READY && (isStart(command))) {
            throw new IllegalArgumentException("이미 게임이 실행중입니다.");
        }
    }

    private boolean isStart(String command) {
        return START.getCommand().equalsIgnoreCase(command);
    }
}
