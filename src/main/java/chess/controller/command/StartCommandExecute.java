package chess.controller.command;

import chess.domain.chessGame.ChessGameState;
import chess.view.OutputView;

public class StartCommandExecute implements CommandExecute {
    private ChessGameState chessGameState;

    public StartCommandExecute(ChessGameState chessGameState) {
        this.chessGameState = chessGameState;
    }

    @Override
    public ChessGameState execute(String ignored1, String ignored2) {
        chessGameState = chessGameState.start();
        OutputView.getInstance().printBoard(chessGameState.getPrintingBoard());
        return chessGameState;
    }
}
