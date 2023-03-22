package controller;

import domain.chessgame.ChessGame;
import domain.position.PositionFactory;
import view.OutputView;

import java.util.List;

public final class Move extends GameStatus {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    Move(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void playTurn(final List<String> inputs) {
        chessGame.move(PositionFactory.createPosition(inputs.get(SOURCE_INDEX)), PositionFactory.createPosition(inputs.get(TARGET_INDEX)));
        final ChessBoardDTO chessBoardDTO = ChessBoardDTO.from(chessGame.getChessBoard());

        OutputView.printChessBoard(chessBoardDTO);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
