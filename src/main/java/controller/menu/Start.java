package controller.menu;

import dto.GameBoardDto;
import model.ChessGame;
import view.OutputView;

public class Start implements Menu {

    @Override
    public void play(ChessGame chessGame, OutputView outputView) {
        chessGame.start();
        printCurrentStatus(chessGame, outputView);
    }

    private void printCurrentStatus(final ChessGame chessGame, final OutputView outputView) {
        outputView.printGameBoard(GameBoardDto.from(chessGame));
        outputView.printCurrentCame(chessGame.getCamp());
    }
}
