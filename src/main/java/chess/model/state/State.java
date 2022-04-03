package chess.model.state;

import chess.model.board.GameResult;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public interface State {

    boolean isFinished();

    boolean isStatus();

    State proceed(List<String> command);

    Map<Position, Piece> getBoard();

    Map<String, Piece> getBoardForWeb();

    GameResult getScore();
}
