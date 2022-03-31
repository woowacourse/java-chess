package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Score;
import chess.domain.piece.Color;
import java.util.Map;

public interface State {

    State movePiece(Position source, Position destination);

    Board getBoard();

    Map<Color, Score> getScore();

    boolean isFinished();
}
