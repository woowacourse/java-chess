package chess.controller.command;

import chess.domain.chessGame.ChessGameState;

public interface CommandExecute {
    ChessGameState execute(String current, String next);
}
