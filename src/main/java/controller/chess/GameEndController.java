package controller.chess;

import service.ChessGameService;
import view.OutputView;

public class GameEndController extends GameController {

    protected GameEndController(ChessGameService chessGameService) {
        super(chessGameService);
    }

    @Override
    public ChessController run() {
        if (chessGameService.isGameEnded()) {
            showGameEndResult();
            chessGameService.deleteChessBoard();
        }
        return this;
    }

    private void showGameEndResult() {
        OutputView.printEndGameMessage();
        OutputView.printStatusResult(chessGameService.calculateScore());
    }

    @Override
    public ChessController readNextController() {
        throw new UnsupportedOperationException("[ERROR] 게임이 종료되어 명령어를 입력할 수 없습니다.");
    }

    @Override
    public boolean isEndController() {
        return true;
    }
}
