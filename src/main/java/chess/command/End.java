package chess.command;

import chess.domain.ChessGame;

public class End implements Command {

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.end();
    }
}
