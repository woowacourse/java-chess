package chess.controller;

import chess.domain.game.state.ChessGame;
import chess.view.OutputView;

public class StartCommand implements Command {
    @Override
    public ChessGame run(ChessGame chessGame) {
        ChessGame startedGame = chessGame.initBoard();
        OutputView.printInitialChessBoard(startedGame.getBoard());
        return startedGame;
    }
}
