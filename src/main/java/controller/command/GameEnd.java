package controller.command;

import service.ChessGameService;
import view.OutputView;

public class GameEnd extends GameCommand {

    protected GameEnd(ChessGameService chessGameService) {
        super(chessGameService);
    }

    @Override
    public Command execute() {
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
    public Command readNextCommand() {
        throw new UnsupportedOperationException("[ERROR] 게임이 종료되어 명령어를 입력할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
