package chess.controller.gamestate;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.CommandRequest;
import java.util.Map;

public interface GameState {

    GameState start(final ChessBoard chessBoard);

    GameState play(final CommandRequest commandRequest);

    GameState end();

    Map<Position, Piece> read();
    
}
