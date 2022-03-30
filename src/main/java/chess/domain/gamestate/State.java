package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.Score;
import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.Map;

public interface State {

    State start();

    State move(Position sourcePosition, Position targetPosition);

    State end();

    boolean isFinished();

    Board getBoard();

    Map<Camp, Score> getScores();

    Camp getWinner();
}
