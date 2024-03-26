package controller.menu;

import model.ChessGame;
import model.Result;
import view.OutputView;

public class Status implements Menu {

    @Override
    public void play(ChessGame chessGame, OutputView outputView) {
        Result result = chessGame.status();
        outputView.printResult(result.getResult());
        outputView.printWinner(result.getWinner());
    }
}
