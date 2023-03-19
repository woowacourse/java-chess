package chess.game;

import chess.game.state.*;
import chess.view.OutputView;

public class ChessGameLauncher {
    private ChessGameState chessGameState;
    
    public ChessGameLauncher() {
        this.chessGameState = new InitGameState();
    }
    
    public void execute() {
        while (chessGameState.runnable()) {
            runChessGame();
        }
    }
    
    private void runChessGame() {
        try {
            chessGameState = chessGameState.next();
        } catch (IllegalStateException illegalStateException) {
            chessGameState = new GameEndState();
            OutputView.println(illegalStateException.getMessage());
        } catch (IllegalArgumentException illegalArgumentException) {
            chessGameState = new OutputChessBoardState(chessGameState);
            OutputView.println(illegalArgumentException.getMessage());
        }
    }
}
