package chess.controller.command;

import chess.controller.result.Result;
import chess.domain.ChessGame;

public interface Command {

    Result execute(ChessGame chessGame);
}
