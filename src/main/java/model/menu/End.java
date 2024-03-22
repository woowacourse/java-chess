package model.menu;

import model.ChessGame;

import java.util.List;

public class End implements ChessStatus {

    @Override
    public ChessStatus play(List<String> input, ChessGame chessGame) {
        throw new IllegalArgumentException("게임이 종료되었습니다");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
