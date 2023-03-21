package controller;

import domain.chessgame.ChessGame;
import domain.position.PositionFactory;

import java.util.List;

public final class Move extends GameStatus {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    Move(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessBoardDTO playTurn(final List<String> inputs) {
        chessGame.move(PositionFactory.createPosition(inputs.get(SOURCE_INDEX)), PositionFactory.createPosition(inputs.get(TARGET_INDEX)));

        return ChessBoardDTO.from(chessGame.getChessBoard());
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
