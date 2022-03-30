package chess.domain.state;

import chess.domain.board.Position;
import chess.domain.board.Result;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

public interface State {

    State movePiece(Position src, Position dest);

    Map<Position, Piece> getBoard();

    Map<Color, Double> getScore();

    Result getResult();

    boolean isFinished();
}
