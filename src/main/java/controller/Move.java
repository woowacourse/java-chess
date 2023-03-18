package controller;

import domain.chessgame.ChessGame;
import domain.coordinate.PositionFactory;

import java.util.List;

public class Move extends GameStatus {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    protected Move(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void playTurn(final List<String> inputs) {
        chessGame.move(PositionFactory.createPosition(inputs.get(SOURCE_INDEX)), PositionFactory.createPosition(inputs.get(TARGET_INDEX)));
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
