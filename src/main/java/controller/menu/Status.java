package controller.menu;

import model.ChessGame;
import model.ScoreCalculator;
import view.OutputView;

public class Status implements Menu {

    @Override
    public void play(ChessGame chessGame, OutputView outputView) {
        ScoreCalculator scoreCalculator = chessGame.status();
        outputView.printResult(scoreCalculator.getResult());
        outputView.printWinner(scoreCalculator.getWinner());
    }
}
