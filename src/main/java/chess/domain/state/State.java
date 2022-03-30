package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.ScoreResult;

public interface State {

    State movePiece(Position source, Position destination);

    Board getBoard();

    ScoreResult getScore();

    boolean isFinished();
}
