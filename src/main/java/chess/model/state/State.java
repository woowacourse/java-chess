package chess.model.state;

import chess.controller.Command;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public interface State {
    
    boolean isFinished();

    Map<Position, Piece> getBoard();

    State execute(Command command);
}
