package chess.domain.command;

import chess.domain.chessGame.ChessGameState;

public class EndCommandExecute implements CommandExecute {
    private final ChessGameState chessGameState;

    public EndCommandExecute(ChessGameState chessGameState) {
        this.chessGameState = chessGameState;
    }

    @Override
    public ChessGameState execute(String current, String next) {
        chessGameState.end();
        return chessGameState;
    }
}
