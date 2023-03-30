package controller.chess;

import service.ChessGameService;
import view.OutputView;

public class StatusController extends GameController {

    protected StatusController(ChessGameService chessGameService) {
        super(chessGameService);
    }

    @Override
    public ChessController run() {
        OutputView.printStatusResult(chessGameService.calculateScore());
        OutputView.printChessBoardState(chessGameService.makeChessBoardState());
        return readNextController();
    }

    @Override
    public boolean isEndController() {
        return false;
    }
}
