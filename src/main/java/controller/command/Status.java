package controller.command;

import dto.ChessGameScoreDto;
import service.ChessGameService;
import view.OutputView;

public class Status extends GameCommand {

    protected Status(ChessGameService chessGameService) {
        super(chessGameService);
    }

    @Override
    public Command execute() {
        OutputView.printStatusResult(chessGameService.calculateScore());
        OutputView.printChessBoardState(chessGameService.makeChessBoardState());
        return readNextCommand();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
