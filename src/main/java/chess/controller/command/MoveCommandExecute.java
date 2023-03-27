package chess.controller.command;

import chess.domain.chessGame.ChessGameState;

public class MoveCommandExecute implements CommandExecute {
    private final ChessGameState chessGameState;

    public MoveCommandExecute(ChessGameState chessGameState) {
        this.chessGameState = chessGameState;
    }

    @Override
    public ChessGameState execute(String current, String next) {
        chessGameState.move(current, next);
        return chessGameState;
    }
}
