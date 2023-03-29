package controller.command;

import service.ChessGameService;
import view.OutputView;

public class Initialize extends GameCommand {

    protected Initialize(ChessGameService chessGameService) {
        super(chessGameService);
    }

    @Override
    public Command execute() {
        OutputView.printChessBoardState(chessGameService.makeChessBoardState());
        return readNextCommand();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
