package controller.chess;

import service.ChessGameService;
import view.OutputView;

public class InitializeController extends GameController {

    protected InitializeController(ChessGameService chessGameService) {
        super(chessGameService);
    }

    @Override
    public ChessController run() {
        OutputView.printChessBoardState(chessGameService.makeChessBoardState());
        return readNextController();
    }

    @Override
    public boolean isEndController() {
        return false;
    }
}
