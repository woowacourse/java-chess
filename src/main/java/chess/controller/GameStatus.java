package chess.controller;

import chess.view.Command;

public enum GameStatus {

    READY,
    RUNNING;

    public void validateCommand(Command command) {
        if (this == READY && (command != Command.START)) {
            throw new IllegalArgumentException("아직 게임이 실행중이지 않습니다.");
        }
        if (this != READY && (command == Command.START)) {
            throw new IllegalArgumentException("이미 게임이 실행중입니다.");
        }
    }
}
