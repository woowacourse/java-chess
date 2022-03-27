package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.MatchResult;
import chess.domain.board.ScoreResult;

public interface State {

    State movePiece(Position src, Position dest);

    Board getBoard();

    ScoreResult getScore();

    boolean isFinished();
}
