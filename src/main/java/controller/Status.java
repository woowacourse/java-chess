package controller;

import domain.chessgame.ChessGame;
import domain.piece.Color;
import view.OutputView;

import java.util.List;

public class Status extends GameStatus{

    protected Status(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void playTurn(final List<String> inputs) {
        final double whiteScore = chessGame.calculateScore(Color.WHITE);
        final double blackScore = chessGame.calculateScore(Color.BLACK);

        OutputView.printNotice("> 중간 결과");
        OutputView.printScore(whiteScore, blackScore);


    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }
}
