package chess.model.state;

import chess.model.board.GameScore;
import chess.model.command.Command;
import chess.model.Team;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public interface State {

    boolean isFinished();

    boolean isSleep();

    GameScore getScores();

    Map<Position, Piece> getBoard();

    State execute(Command command);
}
