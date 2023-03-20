package chess.controller.gamestate;

import chess.domain.ChessBoard;
import chess.dto.CommandRequest;

public interface GameState {

    GameState start();

    GameState play(final ChessBoard chessBoard, final CommandRequest commandRequest);

    GameState end();

}
