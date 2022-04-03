package chess.console.domain.state;

import chess.console.domain.board.Board;
import chess.console.domain.board.Position;
import chess.console.domain.board.Score;
import chess.console.domain.piece.Color;
import java.util.Map;

public interface State {

    State movePiece(Position source, Position destination);

    Board getBoard();

    Map<Color, Score> getScore();

    boolean isFinished();
}
