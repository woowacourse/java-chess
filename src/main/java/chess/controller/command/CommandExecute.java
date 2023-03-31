package chess.controller.command;

import chess.domain.chessGame.ChessGame;

public interface CommandExecute {
    ChessGame execute(String current, String next);
}
