package chess.controller;

import chess.domain.ChessGame;
import chess.utils.DtoAssembler;
import chess.view.OutputView;

public class ChessGameController {
    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printGameStart(DtoAssembler.assemble(chessGame.start()));
    }
}
