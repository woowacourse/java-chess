package chess.model.state;

import chess.model.board.GameScore;
import chess.model.command.Command;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public interface State {

    State execute(Command command);

    boolean isFinished();

    boolean isSleep();

    GameScore getScores();

    Map<Position, Piece> getBoard();
}
