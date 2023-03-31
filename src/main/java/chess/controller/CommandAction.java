package chess.controller;

import chess.controller.status.AppStatus;
import chess.dto.CommandRequest;

public interface CommandAction {

    AppStatus execute(ChessGameController chessGameController, CommandRequest commandRequest);

}
