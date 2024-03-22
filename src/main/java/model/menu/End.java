package model.menu;

import model.GameBoard;

import java.util.List;

public class End implements ChessStatus {

    @Override
    public ChessStatus play(List<String> input, GameBoard gameBoard) {
        throw new IllegalArgumentException("게임이 종료되었습니다");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
